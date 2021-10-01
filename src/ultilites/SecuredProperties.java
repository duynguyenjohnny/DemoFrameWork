/*-
 * #%L
 * Secured Properties
 * ===============================================================
 * Copyright (C) 2016 Brabenetz Harald, Austria
 * ===============================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package ultilites;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.stream.Collectors;

public final class SecuredProperties {

    private SecuredProperties() {
        super();
    }

    public static String getSecretValue(final ultilites.SecuredPropertiesConfig config, final File propertyFile, final String key) {
        return getSecretValues(config, propertyFile, key).get(key);
    }

    public static String getSecretValue(final ultilites.SecuredPropertiesConfig config, final File[] propertyFiles, final String key) {
        return getSecretValues(config, propertyFiles, key).get(key);
    }

    public static Map<String, String> getSecretValues(
            final ultilites.SecuredPropertiesConfig config, final File propertyFile, final String... keys) {
        return getSecretValues(config, new File[] {propertyFile}, keys);
    }

    public static Map<String, String> getSecretValues(
            final ultilites.SecuredPropertiesConfig config, final File[] propertyFiles, final String... keys) {

        Map<String, String> result = new HashMap<>();
        final ultilites.SecretContainer secretContainer = getSecretContainer(config);

        for (File propertyFile : propertyFiles) {
            if (!propertyFile.exists()) {
                continue;
            }
            final Properties properties = SecuredPropertiesUtils.readProperties(propertyFile);
            for (String key : keys) {

                String value = properties.getProperty(key);
                if (Encryption.isEncryptedValue(value)) {
                    // read and decrypt value
                    result.put(key, Encryption.decrypt(secretContainer.getAlgorithm(), secretContainer.getSecretKey(), config.getSaltLength(), value));
                } else {
                    result.put(key, value);
                }
            }

        }

        return result;

    }

    public static void encryptNonEncryptedValues(
            final ultilites.SecuredPropertiesConfig config, final File propertyFile, final String... keys) {
        encryptNonEncryptedValues(config, new File[] {propertyFile}, keys);
    }

    public static void encryptNonEncryptedValues(
            final ultilites.SecuredPropertiesConfig config, final File[] propertyFiles, final String... keys) {

        final ultilites.SecretContainer secretContainer = getSecretContainer(config);
        for (File propertyFile : propertyFiles) {
            if (!propertyFile.exists()) {
                continue;
            }
            Map<String, String> unencryptedValues = new HashMap<>();
            final Properties properties = SecuredPropertiesUtils.readProperties(propertyFile);
            for (String key : keys) {

                String value = properties.getProperty(key);
                if (!Encryption.isEncryptedValue(value) && StringUtils.isNotEmpty(value)) {
                    // replace value with encrypted in property file.
                    unencryptedValues.put(key, value);
                }
            }

            if (!unencryptedValues.isEmpty()) {
                Map<String, String> encryptedValues = encryptValues(config, secretContainer, unencryptedValues);
                Pair<String, String>[] newProperties = encryptedValues.entrySet().stream()
                        .map(e -> Pair.of(e.getKey(), e.getValue()))
                        .collect(Collectors.toSet())
                        .toArray(new Pair[encryptedValues.size()]);
                SecuredPropertiesUtils.replaceSecretValue(propertyFile, newProperties);
            }
        }
    }

    private static Map<String, String> encryptValues(final ultilites.SecuredPropertiesConfig config, final ultilites.SecretContainer secretContainer,
                                                     final Map<String, String> unencryptedValues) {

        Map<String, String> encryptedValues = new HashMap<>();
        for (Entry<String, String> entry : unencryptedValues.entrySet()) {
            encryptedValues.put(entry.getKey(), Encryption.encrypt(
                    secretContainer.getAlgorithm(), secretContainer.getSecretKey(), config.getSaltLength(), entry.getValue()));
        }
        return encryptedValues;
    }

    public static boolean isEncryptedValue(final String maybeEncryptedValue) {
        return Encryption.isEncryptedValue(maybeEncryptedValue);
    }

    public static String encrypt(final ultilites.SecuredPropertiesConfig config, final String plainTextValue) {
        final ultilites.SecretContainer secretContainer = getSecretContainer(config);

        return Encryption.encrypt(secretContainer.getAlgorithm(), secretContainer.getSecretKey(), config.getSaltLength(), plainTextValue);
    }

    public static String decrypt(final ultilites.SecuredPropertiesConfig config, final String encryptedPassword) {
        final ultilites.SecretContainer secretContainer = getSecretContainer(config);

        return Encryption.decrypt(secretContainer.getAlgorithm(), secretContainer.getSecretKey(), config.getSaltLength(), encryptedPassword);

    }

    private static ultilites.SecretContainer getSecretContainer(final ultilites.SecuredPropertiesConfig config) {

        return ultilites.SecretContainerStore.getSecretContainer(config.getSecretFile(), config.isAutoCreateSecretKey(), config.getAllowedAlgorithm());
    }
}

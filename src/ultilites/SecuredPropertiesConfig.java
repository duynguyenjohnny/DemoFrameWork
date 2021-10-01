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

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Arrays;

public class SecuredPropertiesConfig implements Config {

    private static final Logger LOG = LoggerFactory.getLogger(SecuredPropertiesConfig.class);

    private static final int DEFAULT_SALT_LENGTH = 11;

    /** The place of your secret file. default is '$HOME/.secret/securedProperties.key'. */
    private File secretFile;
    /** The salt length to make sure that properties with the same value result in different encrypted strings. */
    private int saltLength = DEFAULT_SALT_LENGTH;
    /** The allowed encryption algorithms for auto-create the secret-key like 'AES_256' or 'DESede_168'. */
    private ultilites.Algorithm[] allowedAlgorithm = new ultilites.Algorithm[] {
            SupportedAlgorithm.AES_256,
            SupportedAlgorithm.AES_192,
            SupportedAlgorithm.AES_128,
            SupportedAlgorithm.DESede_168,
            SupportedAlgorithm.DESede_112
    };

    /** If the secret Key should be created automatically if missing, or an Exception should be shown instead. */
    private boolean autoCreateSecretKey = true;

    /**
     * Return the SecretFile location which is needed to decrypt and encrypt your property-values.
     * <p>
     * Default is $HOME/.secret/securedProperties.key
     */
    public File getSecretFile() {
        if (secretFile == null) {
            final String secretFilePath = SystemUtils.USER_HOME + "/.secret/securedProperties.key";
            LOG.debug("No secretFilePath configured. Use default location: {}", secretFilePath);
            secretFile = new File(secretFilePath);
        }
        return secretFile;
    }

    public int getSaltLength() {
        return saltLength;
    }

    public boolean isAutoCreateSecretKey() {
        return autoCreateSecretKey;
    }

    public ultilites.Algorithm[] getAllowedAlgorithm() {
        return allowedAlgorithm;
    }

    public SecuredPropertiesConfig initDefault() {
        return init(ConfigInitializers.propertyFile(new File("./application.properties")),
                ConfigInitializers.propertyFile(new File("./config/application.properties")),
                ConfigInitializers.envProperties(),
                ConfigInitializers.systemProperties());
    }

    public SecuredPropertiesConfig init(final ultilites.ConfigInitializer... configInitializers) {
        Arrays.asList(configInitializers).forEach(configInit -> configInit.init(this));
        return this;
    }

    @Override
    public SecuredPropertiesConfig withSecretFile(final File newSecretFile) {
        secretFile = newSecretFile;
        return this;
    }

    @Override
    public SecuredPropertiesConfig withSaltLength(final int newSaltLength) {
        saltLength = newSaltLength;
        return this;
    }

    @Override
    public SecuredPropertiesConfig withAllowedAlgorithm(final ultilites.Algorithm... newAllowedAlgorithm) {
        allowedAlgorithm = newAllowedAlgorithm;
        return this;
    }

    public SecuredPropertiesConfig addAllowedAlgorithm(final ultilites.Algorithm... addedAllowedAlgorithm) {
        allowedAlgorithm = ArrayUtils.addAll(allowedAlgorithm, addedAllowedAlgorithm);
        return this;
    }

    @Override
    public SecuredPropertiesConfig withAutoCreateSecretKey(final boolean autoCreate) {
        autoCreateSecretKey = autoCreate;
        return this;
    }

}

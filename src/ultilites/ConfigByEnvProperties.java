/*-
 * #%L
 * Secured Properties
 * ===============================================================
 * Copyright (C) 2016 - 2019 Brabenetz Harald, Austria
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

/**
 * {@link ultilites.ConfigInitializer} implementation for OS environment variables ({@link System#getenv(String)}).
 * <p>
 * The property-Prefix is "SECURED_PROPERTIES".<br>
 * The Keys must be UpperCase like 'SECURED_PROPERTIES_SECRET_FILE'.
 */
public class ConfigByEnvProperties extends ultilites.AbstractConfigInitializer {

    public ConfigByEnvProperties() {
        this(ultilites.ConfigKey.DEFAULT_PREFIX_UPPER_CASE);
    }

    public ConfigByEnvProperties(final String configKeyPrefix) {
        super(configKeyPrefix, ultilites.ConfigKey::getUpperCase, (final ultilites.ConfigKey key) -> configKeyPrefix + "_" + key.getUpperCase());
    }

    @Override
    protected String getValue(final ultilites.ConfigKey key) {
        return System.getenv(getKeyFactory().apply(key));
    }

}

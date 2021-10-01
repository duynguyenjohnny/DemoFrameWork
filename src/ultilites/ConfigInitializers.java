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

import java.io.File;

/**
 * A Collection of known {@link ultilites.ConfigInitializer} factory-methods.
 */
public final class ConfigInitializers {

    private static final ultilites.ConfigInitializer BY_SYSTEM_PROPERTIES_DEFAULT = new ultilites.ConfigBySystemProperties();
    private static final ultilites.ConfigInitializer BY_ENV_PROPERTIES_DEFAULT = new ConfigByEnvProperties();

    private ConfigInitializers() {
        // hide constructor (utility-pattern).
    }

    public static ultilites.ConfigInitializer systemProperties() {
        return BY_SYSTEM_PROPERTIES_DEFAULT;
    }

    public static ultilites.ConfigInitializer envProperties() {
        return BY_ENV_PROPERTIES_DEFAULT;
    }

    public static ultilites.ConfigInitializer propertyFile(final File propertyFile) {
        return new ConfigByPropertyFile(propertyFile);
    }
}

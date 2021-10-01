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

import javax.crypto.SecretKey;

/**
 * A container class to store the values from the secret key file.
 */
public class SecretContainer {

    private final ultilites.Algorithm algorithm;
    private final SecretKey secretKey;

    /**
     * @param algorithm
     *        the {@link .utilities.Algorithm} which was used to generate the secretKey.
     * @param secretKey
     *        the {@link SecretKey}
     */
    public SecretContainer(final ultilites.Algorithm algorithm, final SecretKey secretKey) {
        super();
        this.algorithm = algorithm;
        this.secretKey = secretKey;
    }

    public ultilites.Algorithm getAlgorithm() {
        return this.algorithm;
    }

    public SecretKey getSecretKey() {
        return this.secretKey;
    }

}

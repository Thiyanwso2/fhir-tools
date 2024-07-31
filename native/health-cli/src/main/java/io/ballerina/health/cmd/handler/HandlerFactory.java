/*
 * Copyright (c) 2023, WSO2 LLC. (http://www.wso2.com).
 *
 * WSO2 LLC. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package io.ballerina.health.cmd.handler;

import io.ballerina.health.cmd.core.exception.BallerinaHealthException;
import io.ballerina.health.cmd.core.utils.ErrorMessages;

import java.io.PrintStream;

/**
 * Factory class to create handlers.
 */
public class HandlerFactory {

    public static Handler createHandler(String subCommand, String mode, PrintStream printStream, String specificationPath)
            throws BallerinaHealthException {
        switch (subCommand+":"+mode) {
            case "fhir:template":
                Handler templateHandler = new FhirTemplateGenHandler();
                templateHandler.init(printStream, specificationPath);
                return templateHandler;
            case "fhir:client":
                return new FhirClientGenHandler();
            case "fhir:package":
                Handler packageHandler = new FhirPackageGenHandler();
                packageHandler.init(printStream, specificationPath);
                return packageHandler;
            case "cds:template":
                Handler crdTemplateHnadler = new CrdTemplateGenHandler();
                crdTemplateHnadler.init(printStream, specificationPath);
                return crdTemplateHnadler;
            default:
                throw new BallerinaHealthException(ErrorMessages.INVALID_MODE);
        }
    }
}

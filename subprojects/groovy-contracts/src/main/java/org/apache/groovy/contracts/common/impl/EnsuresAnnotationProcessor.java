/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package org.apache.groovy.contracts.common.impl;

import org.apache.groovy.contracts.common.spi.AnnotationProcessor;
import org.apache.groovy.contracts.common.spi.ProcessingContextInformation;
import org.apache.groovy.contracts.domain.Contract;
import org.apache.groovy.contracts.domain.Postcondition;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.ast.MethodNode;
import org.codehaus.groovy.ast.expr.BooleanExpression;
import org.codehaus.groovy.ast.stmt.BlockStatement;

/**
 * Internal {@link AnnotationProcessor} implementation for post-conditions.
 */
public class EnsuresAnnotationProcessor extends AnnotationProcessor {

    @Override
    public void process(ProcessingContextInformation processingContextInformation, Contract contract, ClassNode classNode, MethodNode methodNode, BlockStatement blockStatement, BooleanExpression booleanExpression) {
        if (!processingContextInformation.isPostconditionsEnabled()) return;
        if (booleanExpression == null) return;

        boolean isConstructor = classNode.getDeclaredConstructors().contains(methodNode);
        contract.postconditions().and(methodNode, new Postcondition(blockStatement, booleanExpression, isConstructor));
    }
}

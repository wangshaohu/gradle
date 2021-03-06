/*
 * Copyright 2015 the original author or authors.
 *
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
 */

package org.gradle.internal.resolve.result;

import org.gradle.api.Nullable;
import org.gradle.api.artifacts.component.ModuleComponentIdentifier;
import org.gradle.internal.resolve.ModuleVersionResolveException;

/**
 * The result of resolving some dynamic version selector to a particular component id.
 */
public interface BuildableComponentSelectionResult extends ResolveResult, ResourceAwareResolveResult {
    static enum State {
        Match, NoMatch, Failed, Unknown
    }

    /**
     * Returns the chosen module component identifier. The component identifier may be null.
     *
     * @return Chosen module component identifier
     */
    ModuleComponentIdentifier getMatch();

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    ModuleVersionResolveException getFailure();

    /**
     * Marks the given module component identifier as matching.
     *
     * @param moduleComponentIdentifier Chosen module component identifier
     */
    void matches(ModuleComponentIdentifier moduleComponentIdentifier);

    void failed(ModuleVersionResolveException failure);

    /**
     * Registers that there was no matching module component identifier.
     */
    void noMatchFound();

    /**
     * Returns the reason for choosing the component.
     */
    State getState();
}

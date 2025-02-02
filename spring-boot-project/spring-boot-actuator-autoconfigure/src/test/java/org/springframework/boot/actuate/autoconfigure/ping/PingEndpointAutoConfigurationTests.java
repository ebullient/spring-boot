/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.boot.actuate.autoconfigure.ping;

import org.junit.jupiter.api.Test;

import org.springframework.boot.actuate.ping.PingEndpoint;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link PingEndpointAutoConfiguration}.
 *
 * @author Erin Schnabel
 */
class PingEndpointAutoConfigurationTests {

	private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
			.withConfiguration(AutoConfigurations.of(PingEndpointAutoConfiguration.class));

	@Test
	void runShouldHaveEndpointBean() {
		this.contextRunner.withPropertyValues("management.endpoint.shutdown.enabled:true")
				.run((context) -> assertThat(context).hasSingleBean(PingEndpoint.class));
	}

	@Test
	void runShouldHaveEndpointBeanEvenIfDefaultIsDisabled() {
		// FIXME
		this.contextRunner.withPropertyValues("management.endpoint.default.enabled:false")
				.run((context) -> assertThat(context).hasSingleBean(PingEndpoint.class));
	}

	@Test
	void runWhenEnabledPropertyIsFalseShouldNotHaveEndpointBean() {
		this.contextRunner.withPropertyValues("management.endpoint.ping.enabled:false")
				.run((context) -> assertThat(context).doesNotHaveBean(PingEndpoint.class));
	}

}

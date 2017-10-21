package co.nums.intellij.aem.errorreports;

import co.nums.intellij.aem.test.junit.sources.BlankStringsSource;
import com.intellij.openapi.application.ApplicationNamesInfo;
import com.intellij.openapi.application.ex.ApplicationInfoEx;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GitHubErrorBeanTest {

	private static final String ANY_ERROR_MESSAGE = "any error message";
	private static final String ANY_STACK_TRACE = "any stack trace";
	private static final String ANY_DESCRIPTION = "any additional info";
	private static final String ANY_PLUGIN_VERSION = "anyPluginVersion";
	private static final String ANY_LAST_ACTION = "anyLastAction";
	private static final SystemInfo ANY_SYSTEM_INFO = mock(SystemInfo.class);
	private static final ApplicationNamesInfo ANY_APP_NAMES_INFO = mock(ApplicationNamesInfo.class);
	private static final ApplicationInfoEx ANY_APP_INFO = anyAppInfo();

	@Test
	void shouldReturnIssueTitleContainingLastEventMessageAndExceptionHash() {
		GitHubErrorBean sut = createGitHubErrorBean("Test error", "I am test stack trace");

		assertThat(sut.getIssueTitle()).isEqualTo("Test error [11a7158f54]");
	}

	@ParameterizedTest
	@BlankStringsSource
	void shouldReturnUnspecifiedIssueTitleWhenErrorMessageIsBlank(String blankErrorMessage) {
		GitHubErrorBean sut = createGitHubErrorBean(blankErrorMessage, "I am test stack trace");

		assertThat(sut.getIssueTitle()).isEqualTo("Unspecified error [11a7158f54]");
	}

	@Test
	void shouldReturnIssueTitleWithHashPlaceholderWhenStackTraceIsNull() {
		GitHubErrorBean sut = createGitHubErrorBean("Test error", null);

		assertThat(sut.getIssueTitle()).isEqualTo("Test error [no-hash]");
	}

	@ParameterizedTest
	@BlankStringsSource
	void shouldReturnIssueTitleWhenErrorMessageIsBlankAndStackTraceIsNull(String blankErrorMessage) {
		GitHubErrorBean sut = createGitHubErrorBean(blankErrorMessage, null);

		assertThat(sut.getIssueTitle()).isEqualTo("Unspecified error [no-hash]");
	}

	@Test
	void shouldReturnIssueDescription() {
		// given
		SystemInfo systemInfo = mock(SystemInfo.class);
		when(systemInfo.getOsName()).thenReturn("Test OS");
		when(systemInfo.getJavaVersion()).thenReturn("100.20.10-Test");
		when(systemInfo.getJavaVmVendor()).thenReturn("Oracle-Test");

		ApplicationNamesInfo appNamesInfo = mock(ApplicationNamesInfo.class);
		when(appNamesInfo.getProductName()).thenReturn("Test product name");
		when(appNamesInfo.getFullProductName()).thenReturn("Test full product name");

		ApplicationInfoEx appInfo = mock(ApplicationInfoEx.class, RETURNS_DEEP_STUBS);
		when(appInfo.getVersionName()).thenReturn("Test App Version Name");
		when(appInfo.isEAP()).thenReturn(true);
		when(appInfo.getBuild().asString()).thenReturn("TEST-BUILD-IC-162.2228.15");
		when(appInfo.getFullVersion()).thenReturn("full.app.version");

		// when
		GitHubErrorBean sut = new GitHubErrorBean(
				ANY_ERROR_MESSAGE,
				"I am artificial stack trace",
				"Additional information provided by user.",
				"TestLastAction",
				"0.0.0-Test",
				systemInfo,
				appNamesInfo,
				appInfo
		);

		// then
		assertThat(sut.getIssueDetails())
				.isEqualTo("" +
						"## Description\n" +
						"Additional information provided by user.\n" +
						"## Exception\n" +
						"```\n" +
						"I am artificial stack trace\n" +
						"```\n" +
						"## Error context\n" +
						"```\n" +
						"Last Action:       TestLastAction\n" +
						"Plugin Version:    0.0.0-Test\n" +
						"OS Name:           Test OS\n" +
						"Java Version:      100.20.10-Test\n" +
						"Java VM Vendor:    Oracle-Test\n" +
						"Product Name:      Test product name\n" +
						"Full Product Name: Test full product name\n" +
						"App Version Name:  Test App Version Name\n" +
						"Is EAP:            true\n" +
						"App Build:         TEST-BUILD-IC-162.2228.15\n" +
						"App Version:       full.app.version\n" +
						"```"
				);
	}

	@ParameterizedTest
	@BlankStringsSource
	void shouldReturnIssueDescriptionWhenDescriptionIsBlank(String blankDescription) {
		GitHubErrorBean sut = new GitHubErrorBean(
				ANY_ERROR_MESSAGE,
				ANY_STACK_TRACE,
				blankDescription,
				ANY_LAST_ACTION,
				ANY_PLUGIN_VERSION,
				ANY_SYSTEM_INFO,
				ANY_APP_NAMES_INFO,
				ANY_APP_INFO
		);

		assertThat(sut.getIssueDetails()).startsWith("## Description\n" +
				"*No description*");
	}

	@Test
	void shouldReturnIssueDescriptionWhenPluginVersionIsNull() {
		GitHubErrorBean sut = new GitHubErrorBean(
				ANY_ERROR_MESSAGE,
				ANY_STACK_TRACE,
				ANY_DESCRIPTION,
				ANY_LAST_ACTION,
				null,
				ANY_SYSTEM_INFO,
				ANY_APP_NAMES_INFO,
				ANY_APP_INFO
		);

		assertThat(sut.getIssueDetails()).contains("Plugin Version:    unknown");
	}

	@Test
	void shouldReturnIssueDescriptionWhenStackTraceIsNull() {
		GitHubErrorBean sut = new GitHubErrorBean(
				ANY_ERROR_MESSAGE,
				null,
				ANY_DESCRIPTION,
				ANY_LAST_ACTION,
				ANY_PLUGIN_VERSION,
				ANY_SYSTEM_INFO,
				ANY_APP_NAMES_INFO,
				ANY_APP_INFO
		);

		assertThat(sut.getIssueDetails()).contains(
				"## Exception\n" +
						"```\n" +
						"invalid stack trace\n" +
						"```\n");
	}

	@Test
	void shouldReturnIssueDescriptionWithoutException() {
		// given
		SystemInfo systemInfo = mock(SystemInfo.class);
		when(systemInfo.getOsName()).thenReturn("Test OS");
		when(systemInfo.getJavaVersion()).thenReturn("100.20.10-Test");
		when(systemInfo.getJavaVmVendor()).thenReturn("Oracle-Test");

		ApplicationNamesInfo appNamesInfo = mock(ApplicationNamesInfo.class);
		when(appNamesInfo.getProductName()).thenReturn("Test product name");
		when(appNamesInfo.getFullProductName()).thenReturn("Test full product name");

		ApplicationInfoEx appInfo = mock(ApplicationInfoEx.class, RETURNS_DEEP_STUBS);
		when(appInfo.getVersionName()).thenReturn("Test App Version Name");
		when(appInfo.isEAP()).thenReturn(true);
		when(appInfo.getBuild().asString()).thenReturn("TEST-BUILD-IC-162.2228.15");
		when(appInfo.getFullVersion()).thenReturn("full.app.version");

		// when
		GitHubErrorBean sut = new GitHubErrorBean(
				ANY_ERROR_MESSAGE,
				ANY_STACK_TRACE,
				"Additional information provided by user.",
				"TestLastAction",
				"0.0.0-Test",
				systemInfo,
				appNamesInfo,
				appInfo
		);

		// then
		assertThat(sut.getIssueDetailsWithoutException())
				.isEqualTo("" +
						"## Description\n" +
						"Additional information provided by user.\n" +
						"## Error context\n" +
						"```\n" +
						"Last Action:       TestLastAction\n" +
						"Plugin Version:    0.0.0-Test\n" +
						"OS Name:           Test OS\n" +
						"Java Version:      100.20.10-Test\n" +
						"Java VM Vendor:    Oracle-Test\n" +
						"Product Name:      Test product name\n" +
						"Full Product Name: Test full product name\n" +
						"App Version Name:  Test App Version Name\n" +
						"Is EAP:            true\n" +
						"App Build:         TEST-BUILD-IC-162.2228.15\n" +
						"App Version:       full.app.version\n" +
						"```"
				);
	}

	@ParameterizedTest
	@BlankStringsSource
	void shouldReturnIssueDescriptionWithoutExceptionWhenDescriptionIsNullOrBlank(String blankDescription) {
		GitHubErrorBean sut = new GitHubErrorBean(
				ANY_ERROR_MESSAGE,
				ANY_STACK_TRACE,
				blankDescription,
				ANY_LAST_ACTION,
				ANY_PLUGIN_VERSION,
				ANY_SYSTEM_INFO,
				ANY_APP_NAMES_INFO,
				ANY_APP_INFO
		);

		assertThat(sut.getIssueDetails()).startsWith("" +
				"## Description\n" +
				"*No description*");
	}

	@Test
	void shouldReturnIssueDescriptionWithoutExceptionWhenPluginVersionIsNull() {
		GitHubErrorBean sut = new GitHubErrorBean(
				ANY_ERROR_MESSAGE,
				ANY_STACK_TRACE,
				ANY_DESCRIPTION,
				ANY_LAST_ACTION,
				null,
				ANY_SYSTEM_INFO,
				ANY_APP_NAMES_INFO,
				ANY_APP_INFO
		);

		assertThat(sut.getIssueDetails()).contains("Plugin Version:    unknown");
	}

	private GitHubErrorBean createGitHubErrorBean(String errorMessage, String stackTrace) {
		return new GitHubErrorBean(
				errorMessage,
				stackTrace,
				ANY_DESCRIPTION,
				ANY_LAST_ACTION,
				ANY_PLUGIN_VERSION,
				ANY_SYSTEM_INFO,
				ANY_APP_NAMES_INFO,
				ANY_APP_INFO
		);
	}

	private static ApplicationInfoEx anyAppInfo() {
		ApplicationInfoEx appInfo = mock(ApplicationInfoEx.class, RETURNS_DEEP_STUBS);
		when(appInfo.getBuild().asString()).thenReturn("any build");
		return appInfo;
	}

}

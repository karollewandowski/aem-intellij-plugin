package co.nums.intellij.aem.htl.extensions;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class StringExtensionsTest {

    @ParameterizedTest
    @CsvSource(value = {
            "properties |property",
            "PROPERTIES |PROPERTY",
            "brushes    |brush",
            "BRUSHES    |BRUSH",
            "dresses    |dress",
            "DRESSES    |DRESS",
            "dogs       |dog",
            "DOGS       |DOG",
            "door       |aDoor",
            "information|anInformation"
    }, delimiter = '|')
    void shouldReturnSingularForm(String plural, String singular) {
        assertThat(StringExtensionsKt.getSingularHtlForm(plural)).isEqualTo(singular);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "components|aComponent",
            "currentPages|aCurrentPage",
            "requests|aRequest",
            "resources|aResource"
    }, delimiter = '|')
    void shouldReturnSingularFormPrependedWithIndefiniteArticleWhenAemGlobalObjectNameIsResult(String plural, String singular) {
        assertThat(StringExtensionsKt.getSingularHtlForm(plural)).isEqualTo(singular);
    }

}

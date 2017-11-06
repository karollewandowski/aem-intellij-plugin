package co.nums.intellij.aem;

import co.nums.intellij.aem.extensions.StringExtensionsKt;
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
        assertThat(StringExtensionsKt.getSingularForm(plural)).isEqualTo(singular);
    }

}

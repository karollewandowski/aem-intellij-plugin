package co.nums.intellij.aem.test.junit.sources;

import org.junit.jupiter.params.provider.CsvSource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@CsvSource({
		",", // simulates null in first CSV column
		"''",
		"'   '"
})
public @interface BlankStringsSource {
}

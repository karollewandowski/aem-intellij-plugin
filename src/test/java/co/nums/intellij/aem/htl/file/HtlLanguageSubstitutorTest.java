package co.nums.intellij.aem.htl.file;

import co.nums.intellij.aem.constants.JcrConstants;
import co.nums.intellij.aem.htl.HtlLanguage;
import com.intellij.ide.highlighter.HtmlFileType;
import com.intellij.lang.Language;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.fileTypes.MockLanguageFileType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(DataProviderRunner.class)
public class HtlLanguageSubstitutorTest {

	private static final Project ANY_PROJECT = mock(Project.class);
	private static final FileType NON_HTL_FILE_TYPE = MockLanguageFileType.INSTANCE;
	private static final VirtualFile JCR_ROOT_DIR = directoryWithName(JcrConstants.JCR_ROOT_DIRECTORY_NAME);

	private HtlLanguageSubstitutor sut;

	private VirtualFile file;

	@Before
	public void setUp() {
		sut = new HtlLanguageSubstitutor();
		file = mock(VirtualFile.class);
	}

	@Test
	@DataProvider(value = {
			"jcr_root",
			"jcr_root/apps",
			"jcr_root/apps/components",
			"home/user/aem-project/test-module/jcr_root",
			"home/user/aem-project/test-module/jcr_root/apps",
			"home/user/aem-project/test-module/jcr_root/apps/components"
	}, splitBy = "/")
	public void shouldSubstituteHtmlFileInJcrRootDirectory(String... path) {
		when(file.getFileType()).thenReturn(HtmlFileType.INSTANCE);
		prepareFileDirectoryPath(file, path);

		Language language = sut.getLanguage(file, ANY_PROJECT);

		assertThat(language).isEqualTo(HtlLanguage.INSTANCE);
	}

	@Test
	public void shouldNotSubstituteNonHtmlFileInJcrRootDirectory() {
		when(file.getFileType()).thenReturn(NON_HTL_FILE_TYPE);
		when(file.getParent()).thenReturn(JCR_ROOT_DIR);

		Language language = sut.getLanguage(file, ANY_PROJECT);

		assertThat(language).isNull();
	}

	@DataProvider(splitBy = "/")
	public static String[] pathsOutOfJcrRoot() {
		return new String[] {
				"not_jcr_root",
				"jcr_root_not",
				"_jcr_root",
				"jcr_root_",
				"jcrroot",
				"not_jcr_root/apps",
				"jcr_root_not/apps",
				"_jcr_root/apps",
				"jcr_root_/apps",
				"jcrroot/apps",
				"home/user/aem-project/test-module/not_jcr_root",
				"home/user/aem-project/test-module/jcr_root_not",
				"home/user/aem-project/test-module/_jcr_root",
				"home/user/aem-project/test-module/jcr_root_",
				"home/user/aem-project/test-module/jcrroot"
		};
	}

	@Test
	@UseDataProvider("pathsOutOfJcrRoot")
	public void shouldNotSubstituteHtmlFileOutOfJcrRootDirectory(String... pathOutOfJcrRoot) {
		when(file.getFileType()).thenReturn(HtmlFileType.INSTANCE);
		prepareFileDirectoryPath(file, pathOutOfJcrRoot);

		Language language = sut.getLanguage(file, ANY_PROJECT);

		assertThat(language).isNull();
	}

	@Test
	@UseDataProvider("pathsOutOfJcrRoot")
	public void shouldNotSubstituteNonHtmlFileOutOfJcrRootDirectory(String... pathOutOfJcrRoot) {
		when(file.getFileType()).thenReturn(NON_HTL_FILE_TYPE);
		prepareFileDirectoryPath(file, pathOutOfJcrRoot);

		Language language = sut.getLanguage(file, ANY_PROJECT);

		assertThat(language).isNull();
	}

	private static void prepareFileDirectoryPath(VirtualFile file, String... path) {
		VirtualFile currentFile = file;
		for (int i = path.length - 1; i >= 0; i--) {
			VirtualFile parentDirectory = directoryWithName(path[i]);
			when(currentFile.getParent()).thenReturn(parentDirectory);
			currentFile = parentDirectory;
		}
	}

	private static VirtualFile directoryWithName(String name) {
		VirtualFile directory = mock(VirtualFile.class);
		when(directory.getName()).thenReturn(name);
		return directory;
	}

}

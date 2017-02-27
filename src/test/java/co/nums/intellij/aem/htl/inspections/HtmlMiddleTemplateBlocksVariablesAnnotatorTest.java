package co.nums.intellij.aem.htl.inspections;

import com.intellij.codeInsight.daemon.impl.AnnotationHolderImpl;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.lang.annotation.AnnotationSession;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Paweł Tarkowski on 18.02.2017.
 */

@RunWith(DataProviderRunner.class)
public class HtmlMiddleTemplateBlocksVariablesAnnotatorTest {
    private HtmlMiddleTemplateBlocksVariablesAnnotator testObject = new HtmlMiddleTemplateBlocksVariablesAnnotator();
    private AnnotationHolderImpl annotationHolder;


    @Before
    public void setUp() {
        annotationHolder = new AnnotationHolderImpl(mock(AnnotationSession.class));
    }

    @DataProvider
    public String[] getHtmlWithNotGlobalBlockVariables() {
        return new String[]{
                "<div class=\"description\">\n" +
                        "    ${properties.jcr:description}\n" +
                        "</div>\n" +
                        "\n" +
                        "<div class=\"title\">\n" +
                        "    <h1 data-sly-use.header=\"co.nums.aem.test.HeaderModel\">${header.mainTitle}</h1>\n" +
                        "    <h2>${header.subTitle}</h2>\n" +
                        "</div>"
        };
    }

    @Test
    @DataProvider("getHtmlWithNotGlobalBlockVariables")
    public void shouldFindNotGlobalBlockVariables(String fullHtml) {
        //given
        PsiElement htmlGlobalElement = createGlobalElement(fullHtml);

        PsiElementVisitor visitor = testObject.buildVisitor(mock(ProblemsHolder.class), true);
        //when

        //then
        //assertTrue(annotationHolder.hasAnnotations());
    }


    @DataProvider
    public String[] getHtmlWithGlobalBlockVariables() {
        return new String[]{
                "<div class=\"description\" data-sly-use.header=\"co.nums.aem.test.HeaderModel\">\n" +
                        "    ${properties.jcr:description}\n" +
                        "</div>\n" +
                        "\n" +
                        "<div class=\"title\">\n" +
                        "    <h1>${header.mainTitle}</h1>\n" +
                        "    <h2>${header.subTitle}</h2>\n" +
                        "</div>"
        };
    }

    @Test
    @DataProvider("getHtmlWithGlobalBlockVariables")
    public void shouldNotFindGlobalBlockVariables(String fullHtml) {
        //given
        PsiElement htmlGlobalElement = createGlobalElement(fullHtml);
        //when
        //testObject.annotate(htmlGlobalElement, annotationHolder);
        //then
        //assertFalse(annotationHolder.hasAnnotations());
    }

    @NotNull
    private PsiElement createGlobalElement(String optionName) {
        PsiElement globalElement = mock(PsiElement.class);
        when(globalElement.getText()).thenReturn(optionName);
        return globalElement;
    }

}


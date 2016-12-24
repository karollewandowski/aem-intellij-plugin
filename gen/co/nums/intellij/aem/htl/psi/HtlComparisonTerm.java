// This is a generated file. Not intended for manual editing.
package co.nums.intellij.aem.htl.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface HtlComparisonTerm extends PsiElement {

  @Nullable
  HtlComparisonOperator getComparisonOperator();

  @NotNull
  List<HtlFactor> getFactorList();

}

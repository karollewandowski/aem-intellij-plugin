// This is a generated file. Not intended for manual editing.
package co.nums.intellij.aem.htl.psi.impl;

import java.util.List;

import co.nums.intellij.aem.htl.psi.HtlVisitor;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import co.nums.intellij.aem.htl.psi.HtlComparisonOp;
import co.nums.intellij.aem.htl.psi.HtlComparisonTerm;
import co.nums.intellij.aem.htl.psi.HtlFactor;

public class HtlComparisonTermImpl extends ASTWrapperPsiElement implements HtlComparisonTerm {

  public HtlComparisonTermImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull HtlVisitor visitor) {
    visitor.visitComparisonTerm(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof HtlVisitor) accept((HtlVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public HtlComparisonOp getComparisonOp() {
    return findChildByClass(HtlComparisonOp.class);
  }

  @Override
  @NotNull
  public List<HtlFactor> getFactorList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, HtlFactor.class);
  }

}

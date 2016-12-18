// This is a generated file. Not intended for manual editing.
package co.nums.intellij.aem.htl.psi.impl;

import co.nums.intellij.aem.htl.psi.HtlVisitor;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import co.nums.intellij.aem.htl.psi.HtlComparisonOp;

public class HtlComparisonOpImpl extends ASTWrapperPsiElement implements HtlComparisonOp {

  public HtlComparisonOpImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull HtlVisitor visitor) {
    visitor.visitComparisonOp(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof HtlVisitor) accept((HtlVisitor)visitor);
    else super.accept(visitor);
  }

}

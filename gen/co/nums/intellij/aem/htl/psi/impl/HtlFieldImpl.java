// This is a generated file. Not intended for manual editing.
package co.nums.intellij.aem.htl.psi.impl;

import co.nums.intellij.aem.htl.psi.HtlField;
import co.nums.intellij.aem.htl.psi.HtlVisitor;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;

import static co.nums.intellij.aem.htl.psi.HtlTokenTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;

public class HtlFieldImpl extends ASTWrapperPsiElement implements HtlField {

  public HtlFieldImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull HtlVisitor visitor) {
    visitor.visitField(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof HtlVisitor) accept((HtlVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public PsiElement getIdentifier() {
    return findNotNullChildByType(IDENTIFIER);
  }

}

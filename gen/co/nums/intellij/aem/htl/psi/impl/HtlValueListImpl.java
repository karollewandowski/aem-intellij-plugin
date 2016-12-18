// This is a generated file. Not intended for manual editing.
package co.nums.intellij.aem.htl.psi.impl;

import java.util.List;

import co.nums.intellij.aem.htl.psi.HtlValueList;
import co.nums.intellij.aem.htl.psi.HtlVisitor;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import co.nums.intellij.aem.htl.psi.HtlExprNode;

public class HtlValueListImpl extends ASTWrapperPsiElement implements HtlValueList {

  public HtlValueListImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull HtlVisitor visitor) {
    visitor.visitValueList(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof HtlVisitor) accept((HtlVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<HtlExprNode> getExprNodeList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, HtlExprNode.class);
  }

}

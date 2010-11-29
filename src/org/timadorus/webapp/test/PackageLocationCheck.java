/**
 * 
 */
package org.timadorus.webapp.test;

import java.io.File;

import com.puppycrawl.tools.checkstyle.api.Check;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FullIdent;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

/**
 * @author sage
 *
 */
public class PackageLocationCheck extends Check {
  
  protected String[] pathElements;
  
  private final String separatorRegex;
  
  public PackageLocationCheck() {
    if (File.separatorChar == '\\') {
      separatorRegex = "\\\\";
    } else {
      separatorRegex = File.separator;
    }
  }
  
  @Override
  public int[] getDefaultTokens() {
    return new int[]{TokenTypes.PACKAGE_DEF};
  }

  @Override
  public void beginTree(DetailAST rootAst) {
    String filename = getFileContents().getFilename();
    //System.err.println("filename is " + filename);
    
    String[] tmpArr = filename.split(separatorRegex);
    // strip the filename
    pathElements = new String[tmpArr.length - 1];
    for (int i = 0; i < pathElements.length; i++) { pathElements[i] = tmpArr[i]; }
  }


  // TODO: not very elegant to squash the tree for FullIdent.getText() and then slit the ident again
  
  public void visitToken(DetailAST ast) {
    // rebuild the package name
    final DetailAST nameAST = ast.getLastChild().getPreviousSibling();
    final FullIdent full = FullIdent.createFullIdent(nameAST);
    if (!matchElems(full.getText())) {
      log(full.getLineNo(),
          full.getColumnNo(),
          "package name '{0}' and directory '{1}' containing the source file do not match",
          full.getText(),
          joinString(pathElements, File.separator));
    }
  }

  private String joinString(String[] array, String separator) {
    StringBuilder text = new StringBuilder();
    String currSeparator = "";
    
    for (String elem : array) {
      text.append(currSeparator);
      text.append(elem);
      currSeparator = separator;
    }
    return text.toString();
  }

  private boolean matchElems(String packName) {
    String[] packageNameElems = packName.split("\\.");
    
    //  System.err.println("packageNameElems.length: " + packageNameElems.length 
    //                     + "pathElements.length: " + pathElements.length);
    
    // if the package name has more elements than the path something cannot be right
    if (packageNameElems.length > pathElements.length) { return false; }
    
    // compare the elements
    for (int i = pathElements.length - 1, j = packageNameElems.length - 1; 
        j >= 0; 
        i--, j--) {
      if (!pathElements[i].equals(packageNameElems[j])) { return false; }
    }
    return true;
  }
  
}

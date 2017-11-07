package co.nums.intellij.aem.htl.documentation

class HtlBlocksDocumentationProviderTest : HtlDocumentationProviderTestBase() {

    fun testAttributeBlockDoc() = doTest(
            """
            <div data-sly-attribute=""></div>
                      ^
            """, """
            <code>data-sly-attribute</code>
            <p>Sets an attribute or a group of attributes on the current element (<code>style</code> and <code>on</code>* attributes are not supported due to XSS vulnerability).</p>
            <ul>
            <li><strong>Element:</strong> always shown</li>
            <li><strong>Content of element:</strong> always shown</li>
            <li><strong>Attribute value:</strong> optional; <code>String</code> for setting attribute content, or <code>Boolean</code> for setting boolean attributes, or <code>Object</code> for setting multiple attributes; removes the attribute if the value is omitted.</li>
            <li><strong>Attribute identifier:</strong> optional; the attribute name; must be omitted only if attribute value is an <code>Object</code>.</li>
            </ul>
            """
    )

    fun testCallBlockDoc() = doTest(
            """
            <div data-sly-call=""></div>
                      ^
            """, """
            <code>data-sly-call</code>
            <p>Calls a declared HTML block, passing parameters to it.</p>
            <ul>
            <li><strong>Element:</strong> always shown</li>
            <li><strong>Content of element:</strong> replaced with the content of the called <code>data-sly-template</code> element</li>
            <li><strong>Attribute value:</strong> optional; an expression defining the template identifier and the parameters to pass</li>
            <li><strong>Attribute identifier:</strong> none</li>
            </ul>
            """
    )

    fun testElementBlockDoc() = doTest(
            """
            <div data-sly-element=""></div>
                      ^
            """, """
            <code>data-sly-element</code>
            <p>Replaces the element's tag name.</p>
            <ul>
            <li><strong>Element:</strong> always shown</li>
            <li><strong>Content of element:</strong> always shown</li>
            <li><strong>Attribute value:</strong> required; <code>String</code>; the element's tag name</li>
            <li><strong>Attribute identifier:</strong> none</li>
            </ul>
            """
    )

    fun testIncludeBlockDoc() = doTest(
            """
            <div data-sly-include=""></div>
                      ^
            """, """
            <code>data-sly-include</code>
            <p>Includes the output of a rendering script run with the current context.</p>
            <ul>
            <li><strong>Element:</strong> always shown</li>
            <li><strong>Content of element:</strong> replaced with the content of the included script</li>
            <li><strong>Attribute value:</strong> required; the file to include</li>
            <li><strong>Attribute identifier:</strong> none</li>
            </ul>
            """
    )

    fun testListBlockDoc() = doTest(
            """
            <div data-sly-list=""></div>
                      ^
            """, """
            <code>data-sly-list</code>
            <p>Iterates over the content of each item in the attribute value.</p>
            <ul>
            <li><strong>Element:</strong> shown only if the number of items from the attribute value is greater than 0, or if the attribute value is a string or number</li>
            <li><strong>Content of element:</strong> repeated as many times as there are items in the attribute value</li>
            <li><strong>Attribute value:</strong> optional; the item to iterate over; if omitted the content will not be shown</li>
            <li><strong>Attribute identifier:</strong> optional; customised identifier name to access the item within the list element</li>
            </ul>
            """
    )

    fun testRepeatBlockDoc() = doTest(
            """
            <div data-sly-repeat=""></div>
                      ^
            """, """
            <code>data-sly-repeat</code>
            <p>Iterates over the content of each item in the attribute value and displays the containing element as many times as items in the attribute value.</p>
            <ul>
            <li><strong>Element:</strong> shown only if the number of items from the attribute value is greater than 0, or if the attribute value is a string or number</li>
            <li><strong>Content of element:</strong> repeated as many times as there are items in the attribute value</li>
            <li><strong>Attribute value:</strong> optional; the item to iterate over; if omitted the containing element and its content will not be shown</li>
            <li><strong>Attribute identifier:</strong> optional; customised identifier name to access the item within the repeat element</li>
            </ul>
            """
    )

    fun testResourceBlockDoc() = doTest(
            """
            <div data-sly-resource=""></div>
                      ^
            """, """
            <code>data-sly-resource</code>
            <p>Includes a rendered resource.</p>
            <ul>
            <li><strong>Element:</strong> always shown</li>
            <li><strong>Content of element:</strong> replaced with the content of the resource</li>
            <li><strong>Attribute value:</strong> required; the path to include</li>
            <li><strong>Attribute identifier:</strong> none</li>
            </ul>
            """
    )

    fun testTemplateBlockDoc() = doTest(
            """
            <div data-sly-template=""></div>
                      ^
            """, """
            <code>data-sly-template</code>
            <p>Declares an HTML block, naming it with an identifier and defining the parameters it can get.</p>
            <ul>
            <li><strong>Element:</strong> never shown</li>
            <li><strong>Content of element:</strong> shown upon calling the template with <code>data-sly-call</code></li>
            <li><strong>Attribute value:</strong> optional; an expression with only options, defining the parameters it can get</li>
            <li><strong>Attribute identifier:</strong> required; the template identifier to declare</li>
            </ul>
            """
    )

    fun testTestBlockDoc() = doTest(
            """
            <div data-sly-test=""></div>
                      ^
            """, """
            <code>data-sly-test</code>
            <p>Keeps, or removes the element depending on the attribute value.</p>
            <ul>
            <li><strong>Element:</strong> shown if test evaluates to <code>true</code></li>
            <li><strong>Content of element:</strong> shown if test evaluates to <code>true</code></li>
            <li><strong>Attribute value:</strong> optional; evaluated as <code>Boolean</code> (but not type-cased to <code>Boolean</code> when exposed in a variable); evaluates to <code>false</code> if the value is omitted</li>
            <li><strong>Attribute identifier:</strong> optional; identifier name to access the result of the test</li>
            </ul>
            """
    )

    fun testTextBlockDoc() = doTest(
            """
            <div data-sly-text=""></div>
                      ^
            """, """
            <code>data-sly-text</code>
            <p>Sets the content for the current element.</p>
            <ul>
            <li><strong>Element:</strong> always shown</li>
            <li><strong>Content of element:</strong> replaced with evaluated result</li>
            <li><strong>Attribute value:</strong> required; evaluates to <code>String</code>; the element content</li>
            <li><strong>Attribute identifier:</strong> none</li>
            </ul>
            """
    )

    fun testUnwrapBlockDoc() = doTest(
            """
            <div data-sly-unwrap=""></div>
                      ^
            """, """
            <code>data-sly-unwrap</code>
            <p>Unwraps the element.</p>
            <ul>
            <li><strong>Element:</strong> never shown</li>
            <li><strong>Content of element:</strong> always shown</li>
            <li><strong>Attribute value:</strong> none</li>
            <li><strong>Attribute identifier:</strong> none</li>
            </ul>
            """
    )

    fun testUseBlockDoc() = doTest(
            """
            <div data-sly-use=""></div>
                      ^
            """, """
            <code>data-sly-use</code>
            <p>Exposes logic to the template.</p>
            <ul>
            <li><strong>Element:</strong> always shown</li>

            <li><strong>Attribute value:</strong> required; evaluates to <code>String</code>; the object to instantiate</li>
            <li><strong>Attribute identifier:</strong> optional; customised identifier name to access the instantiated logic</li>
            </ul>
            """
    )

}

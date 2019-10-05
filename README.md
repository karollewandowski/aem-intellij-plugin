<!--
[![Build Status](https://travis-ci.org/karollewandowski/aem-intellij-plugin.svg?branch=master)](https://travis-ci.org/karollewandowski/aem-intellij-plugin)
 [![codecov](https://codecov.io/gh/karollewandowski/aem-intellij-plugin/branch/master/graph/badge.svg)](https://codecov.io/gh/karollewandowski/aem-intellij-plugin) 
[![codebeat](https://codebeat.co/badges/83dbd668-d574-4be5-b7fb-8b5ae6fdaf8b)](https://codebeat.co/projects/github-com-karollewandowski-aem-intellij-plugin)
-->
[![Downloads](https://img.shields.io/jetbrains/plugin/d/9269-aem-intellij-plugin.svg)](https://plugins.jetbrains.com/plugin/9269-aem-intellij-plugin)

:no_entry: :no_entry: :no_entry:
*Plugin code in this repository is not maintained any more. The only maintained file will be README.md with list of features. **Reporting issues is still appreciated in this repository.*** :no_entry: :no_entry: :no_entry:

**Plugin is still developed and maintained, it is just moved to private repository.**

# AEM IntelliJ Plugin

IntelliJ platform plugin for Adobe Experience Manager. Plugin is compatible with all IntelliJ Platform products.

**Reporting issues and requesting features is highly appreciated**.


## Installation

Plugin can be installed from JetBrains plugins repository:

Go to: `Preferences` / `Plugins` / `Browse Repositories...` and search for `AEM IntelliJ Plugin`.


## Features

* HTL/Sightly (1.0 - 1.4) support
  * syntax validation and highlighting
  * autocompletion, inspections, refactoring and documentation for:
    * block attributes (`data-sly-*`)
    * global objects (`properties`, `currentPage`, `wcmmode`, etc.)
    * properties (`jcr:title`, `cq:lastModified`, `sling:resourceType`, etc.)
    * built-in expression options (`context`, `addSelector`, `i18n`, etc.)
    * display contexts (`html`, `scriptToken`, `unsafe`, etc.)
    * use objects (Use API objects and Sling Models)
    * block variables (eg. `data-sly-use.variable`)
    * template parameters (eg. <code>data-sly-template.myTemplate="${ @ param1, param2}"</code>)

# Presentation "CloudSim Plus: A modern, full-featured, highly extensible and easier-to-use Java 8 Framework for Modeling and Simulation of Cloud Computing Infrastructures and Services" [![Creative Commons](https://img.shields.io/badge/license-CC--BY--SA%204.0-orange.svg?style=flat-square)](http://creativecommons.org/licenses/by-sa/4.0/)

Contains an HTML presentation using the [reveal.js](https://github.com/hakimel/reveal.js) library. The presentation can be accessed [online here](http://cloudsimplus.org/presentation/).

If you cloned the repository at your computer, to launch the presentation you have to just open the [index.html](index.html) at a web browser.

## Changing the style sheets

If you change some [scss](css) files ([SaaS files](http://sass-lang.com)), you have to re-generate the css files using [grunt](https://gruntjs.com): 

```bash
npm install -g grunt
npm install -g grunt-cli
npm install
grunt
```

# Exporting to PDF
To generate a PDF file, just open the presentation in Google Chrome locally 
(you don't need to run it with a local server) and include the parameter `?print-pdf` at the URL.
Then print as usual.

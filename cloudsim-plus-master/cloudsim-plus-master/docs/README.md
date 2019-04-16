# CloudSim Plus Documentation

This directory contains several different kinds of CloudSim Plus documentation, including a:
- FAQs, guides and [JavaDocs](javadocs) in [reStructuredText (rst)](https://en.wikipedia.org/wiki/ReStructuredText) format to be published at [ReadTheDocs site](http://cloudsimplus.rtfd.io);
- [StarUML](http://staruml.io) project containing [UML diagrams](cloudsim-plus.staruml.mdj); 
- [Side-by-Side comparison between CloudSim and CloudSim Plus java simulation scenarios](CloudSim-and-CloudSimPlus-Comparison.html) (online version available [here](http://cloudsimplus.org/CloudSim-and-CloudSimPlus-Comparison.html));
- [White Paper](cloudsim-plus-white-paper.pdf) published at the [EU/Brasil Cloud FORUM](https://eubrasilcloudforum.eu).
- [Web Slides](presentation/index.html) presenting CloudSim Plus (online version available [here](http://cloudsimplus.org/presentation/)).

## Building the reStructuredText files for the Documentation Site
To generate the Sphinx documentation to publish at the [ReadTheDocs](http://cloudsimplus.rtfd.io) or to read locally, considering you have python installed, you can execute the commands below to install the additional tools:

```bash
#Install pip to download python packages (can also be installed via package managers in Linux and macOS)
sudo curl https://bootstrap.pypa.io/get-pip.py | python

#Install sphinx and its build tools to locally build the rst documents to html, latex, epub or other formats
pip install sphinx sphinx-autobuild 

#Install a Sphinx extension to parse javadocs comments inside Java files and generate rst files for Sphinx
pip install javasphinx-apidoc
```

Ensure that you have the following environments variables declared, since the python scripts use UTF-8 encoding:

```bash
export LC_ALL=en_US.UTF-8
export LANG=en_US.UTF-8
```

To generate the rst files from the javadoc comments inside the Java source files use:

```shell
make javadoc
```

To build the documentation in html use:
```shell
make html
```

After that, you can navigate the generated documentation locally by accessing the [_build](_build) directory. To publish the updated documentation to ReadTheDocs site, just commit any changes (which don't include the _build directory) and push them to GitHub.

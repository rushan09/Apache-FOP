# Apache-FOP

This project includes a method of generating pdf files using xml data.

#### Follow these tutorials to get a good understanding about apache FOP.
######--References

http://w3schools.sinsixx.com/xslfo/default.asp.htm

http://altova-aot.s3.amazonaws.com/Altova%20XSLT%20Technology/player.html

### Sample basic xsl code
```
<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format">
<xsl:output method="xml" indent="yes" version="1.0" encoding="UTF-8"/>

<xsl:template match="/">
	<fo:root>
		<fo:layout-master-set>
			<fo:simple-page-master master-name="refference_name">
				<fo:region-body></fo:region-body>
			</fo:simple-page-master>
		</fo:layout-master-set>
		<fo:page-sequence master-reference="refference_name">
			<fo:flow flow-name="xsl-region-body">
				<fo:block></fo:block>
			</fo:flow>
		</fo:page-sequence>
	</fo:root>

</xsl:template>

</xsl:stylesheet>

```



<!DOCTYPE html [
    <!ENTITY nbsp "&#160;"> 
    <!ENTITY copy "&#xA9;"> 
]>

<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes" />
<xsl:template match="/">
	<html>
	<head><title>HEMOGRAMS OF THE PATIENT</title></head>
	<p><b>Name: </b><xsl:value-of select="/patient/@name"/> </p>
	<p><b>Surname: </b><xsl:value-of select="/patient/@surname" /></p>
	<p>HEMOGRAMS:
		<xsl:for-each select="/patient/hemograms/hemogram">
		<p>Hemogram with date: <xsl:value-of select="dob"/></p>
		<table border="1">
		<tr>
			<th>Feature</th>
			<th>Value</th>
			<th>Healthy</th>
		</tr>
		
		<xsl:for-each select="featureValues/featureValue">
		<tr>
			<td>
				<xsl:value-of select="feature/name" />
			</td>
			<td>
				<xsl:value-of select="value" />
			</td>
			<td>
				<xsl:value-of select="healthy" />
			</td>
		</tr>
		</xsl:for-each>
		</table>
		<xsl:value-of select="comments"/>
		</xsl:for-each>
	</p>
	</html>
</xsl:template>
</xsl:stylesheet>
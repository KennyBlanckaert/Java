<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="xml"/>
    <xsl:template match="node()|@*">
        <xsl:copy>
            <xsl:apply-templates/>
        </xsl:copy>
    </xsl:template> 

    <xsl:template match="firstname">
        <voornaam>
            <xsl:value-of select="text()" />
        </voornaam>
    </xsl:template>
    
    <xsl:template match="lastname">
        <achternaam>
            <xsl:value-of select="text()" />
        </achternaam>
    </xsl:template>
</xsl:stylesheet>

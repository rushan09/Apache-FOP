<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format">

<xsl:import href="response1.xsl"/>
<xsl:output version="1.0" method="xml" indent="yes"/>
<xsl:variable name="ptitle" select="'Report-Voters with disabilities'"/>
<xsl:variable name="psubtitle" select="'Assisted by companions'"/>

<xsl:template match="/">
	<fo:root>
		<fo:layout-master-set>
			<fo:simple-page-master master-name="votes_list" margin="3mm">
				<fo:region-body margin="1in">
				</fo:region-body>
				<fo:region-before extent="1in">
				</fo:region-before>
				<fo:region-after extent="1in">
				</fo:region-after>
			</fo:simple-page-master>
		</fo:layout-master-set>
		<fo:page-sequence master-reference="votes_list">
		
			<xsl:apply-imports></xsl:apply-imports>
				
			 <fo:flow flow-name="xsl-region-body">
				<xsl:apply-templates select="/dataset">
				</xsl:apply-templates>
			</fo:flow> 
		</fo:page-sequence>
	</fo:root>
</xsl:template>

<xsl:template match="/dataset">
		<fo:table inline-progression-dimension="auto">
			<fo:table-column column-width="auto" border="solid 0.2mm black" />
				<fo:table-column column-width="auto" border="solid 0.2mm black" />
				<fo:table-column column-width="auto" border="solid 0.2mm black" />
				<fo:table-column column-width="auto" border="solid 0.2mm black" />
				<fo:table-column column-width="auto" border="solid 0.2mm black" />
				<fo:table-column column-width="auto" border="solid 0.2mm black" />
				<fo:table-column column-width="auto" border="solid 0.2mm black" />
				<fo:table-column column-width="auto" border="solid 0.2mm black" />
				<fo:table-column column-width="auto" border="solid 0.2mm black" />

				<fo:table-header font="8pt Arial" color="MEDIUMBLUE">
					<fo:table-row>
						<fo:table-cell padding="1pt">
							<fo:block font-weight="bold">Polling District</fo:block>
						</fo:table-cell>
						<fo:table-cell padding="1pt">
							<fo:block font-weight="bold">Ward</fo:block>
						</fo:table-cell>
						<fo:table-cell padding="1pt">
							<fo:block font-weight="bold">Polling Station</fo:block>
						</fo:table-cell>
						<fo:table-cell padding="1pt">
							<fo:block font-weight="bold">Presiding Officer</fo:block>
						</fo:table-cell>
						<fo:table-cell padding="1pt">
							<fo:block font-weight="bold">Elector's Number</fo:block>
						</fo:table-cell>
						<fo:table-cell padding="1pt">
							<fo:block font-weight="bold">Name of Voter</fo:block>
						</fo:table-cell>
						<fo:table-cell padding="1pt">
							<fo:block font-weight="bold">Companion Name</fo:block>
						</fo:table-cell>
						<fo:table-cell padding="1pt">
							<fo:block font-weight="bold">Companion Elector's Number
							</fo:block>
						</fo:table-cell>
						<fo:table-cell padding="1pt">
							<fo:block font-weight="bold">Companion Address</fo:block>
						</fo:table-cell>
					</fo:table-row>
				</fo:table-header>



				<fo:table-body font="7pt Arial" color="DARKSLATEGRAY">

					<xsl:for-each select="//record">
						<fo:table-row border="solid 0.1mm black">


							<fo:table-cell padding="1pt">
								<fo:block>
									<xsl:value-of select="district"/>
								</fo:block>
							</fo:table-cell>
							<fo:table-cell padding="1pt">
								<fo:block>
									<xsl:value-of select='ward' />
								</fo:block>
							</fo:table-cell>
							<fo:table-cell padding="1pt">
								<fo:block>
									<xsl:value-of select='pollingstationname' />
								</fo:block>
							</fo:table-cell>
							<fo:table-cell padding="1pt">
								<fo:block>
									<xsl:value-of select='pofficername' />
								</fo:block>
							</fo:table-cell>
							<fo:table-cell padding="1pt">
								<fo:block>
									<xsl:value-of select='electornumber' />
								</fo:block>
							</fo:table-cell>
							<fo:table-cell padding="1pt">
								<fo:block>
									<xsl:value-of select='votername' />
								</fo:block>
							</fo:table-cell>
							<fo:table-cell padding="1pt">
								<fo:block>
									<xsl:value-of select='companionname' />
								</fo:block>
							</fo:table-cell>
							<fo:table-cell padding="1pt">
								<fo:block>
									<xsl:value-of select='companionnumber' />
								</fo:block>
							</fo:table-cell>
							<fo:table-cell padding="1pt">
								<fo:block>
									<xsl:value-of select='companionaddress' />
								</fo:block>
							</fo:table-cell>
						</fo:table-row>
					</xsl:for-each>
				</fo:table-body>
	
		</fo:table>
	
</xsl:template>

</xsl:stylesheet>

<?xml version="1.0" encoding="GBK"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
	xmlns:bg="bpm.graphic" 
	xmlns:ciied="com.ibm.ilog.elixir.diagram"

	xmlns:calc="xalan://com.htsoft.core.jbpm.jpdl.graph.TransformUtil"
	extension-element-prefixes="calc"	
	>
	<xsl:param name="name" />
	<xsl:template match="/diagram">
		<process xmlns="http://jbpm.org/4.4/jpdl">
			<xsl:attribute name="name">
				<xsl:value-of select="$name"></xsl:value-of>
			</xsl:attribute>
<!-- 			<xsl:apply-templates /> -->
			<xsl:call-template name="sub-process"></xsl:call-template>
			<xsl:call-template name="start"></xsl:call-template>
			<xsl:call-template name="task"></xsl:call-template>
			<xsl:call-template name="decision"></xsl:call-template>
			<xsl:call-template name="join"></xsl:call-template>
			<xsl:call-template name="fork"></xsl:call-template>
			
			<xsl:call-template name="end"></xsl:call-template>
			<xsl:call-template name="end-error"></xsl:call-template>
			<xsl:call-template name="end-cancel"></xsl:call-template>
		</process>
	</xsl:template>

	<!-- 开始 -->
	<xsl:template name="start">
		<xsl:for-each select="bg:StartEvent">
			<start>
				<xsl:call-template name="attr_comp_name_and_g"></xsl:call-template>
				<xsl:call-template name="attr_transition"></xsl:call-template>
			</start>
		</xsl:for-each>
	</xsl:template>



	<!-- 任务 -->
	<xsl:template name="task">
		<xsl:for-each select="bg:Task[@user='true']">
			<task>
				<xsl:call-template name="attr_comp_name_and_g"></xsl:call-template>
				<xsl:call-template name="attr_transition"></xsl:call-template>
			</task>
		</xsl:for-each>
	</xsl:template>

	<!-- 决策 -->
	<xsl:template name="decision">
		<xsl:for-each select="bg:Gateway[not(gatewayType)]">
			<decision>
				<xsl:call-template name="attr_comp_name_and_g"></xsl:call-template>
				<xsl:call-template name="attr_transition"></xsl:call-template>
				<handler class="com.htsoft.oa.workflow.handler.DecisionHandlerImpl"/>
			</decision>
		</xsl:for-each>
	</xsl:template>

	<!-- 串行 -->
	<xsl:template name="join">
		<xsl:for-each select="bg:Gateway[gatewayType='AND']">
			<join>
				<xsl:call-template name="attr_comp_name_and_g"></xsl:call-template>
				<xsl:call-template name="attr_transition"></xsl:call-template>
			</join>
		</xsl:for-each>
	</xsl:template>

	<!-- 并行 -->
	<xsl:template name="fork">
		<xsl:for-each select="bg:Gateway[gatewayType='OR']">
			<fork>
				<xsl:call-template name="attr_comp_name_and_g"></xsl:call-template>
				<xsl:call-template name="attr_transition"></xsl:call-template>
			</fork>
		</xsl:for-each>
	</xsl:template>

	<!-- 子过程 -->
	<xsl:template name="sub-process">
		<xsl:for-each select="bg:Task[@startSubFlow='true']">
			<sub-process >
				<xsl:attribute name="sub-process-key">
					<xsl:value-of select="subDefKey"></xsl:value-of>
				</xsl:attribute>
				<xsl:attribute name="outcome">
					<xsl:text>#{toParentPath}</xsl:text>
				</xsl:attribute>
				<xsl:call-template name="attr_comp_name_and_g"></xsl:call-template>
				<xsl:call-template name="attr_transition"></xsl:call-template>
			</sub-process>
		</xsl:for-each>
	</xsl:template>

	<xsl:template name="end">
		<xsl:for-each select="bg:EndEvent[not(trigger)]">
			<end>
				<xsl:call-template name="attr_comp_name_and_g" />
			</end>
		</xsl:for-each>
	</xsl:template>



	<xsl:template name="end-error">
		<xsl:for-each select="bg:EndEvent[trigger='Error']">
			<end-error>
				<xsl:call-template name="attr_comp_name_and_g" />

			</end-error>
		</xsl:for-each>
	</xsl:template>

	<xsl:template name="end-cancel">
		<xsl:for-each select="bg:EndEvent[trigger='Cancel']">
			<end-cancel>
				<xsl:call-template name="attr_comp_name_and_g" />
			</end-cancel>
		</xsl:for-each>
	</xsl:template>



	<xsl:template name="attr_comp_name_and_g">
		<xsl:attribute name="name">
			<xsl:value-of select="label"></xsl:value-of>
		</xsl:attribute>
		
		<xsl:variable name="parentNode" select="parent::*"></xsl:variable>					
		
		<xsl:attribute name="g">
				<xsl:choose>
					<xsl:when test="name(.)='bg:StartEvent'">
						<xsl:value-of select="@x -6"></xsl:value-of>,<xsl:value-of select="@y -6"></xsl:value-of>,<xsl:value-of select="@width"></xsl:value-of>,<xsl:value-of select="@height"></xsl:value-of>
					</xsl:when>
					<xsl:when test="name(.)='bg:EndEvent'">
						<xsl:value-of select="@x -6"></xsl:value-of>,<xsl:value-of select="@y -6"></xsl:value-of>,<xsl:value-of select="@width"></xsl:value-of>,<xsl:value-of select="@height"></xsl:value-of>
					</xsl:when>
					<xsl:when test="name(.)='bg:Gateway'">
						<xsl:value-of select="@x +1"></xsl:value-of>,<xsl:value-of select="@y +1"></xsl:value-of>,<xsl:value-of select="@width"></xsl:value-of>,<xsl:value-of select="@height"></xsl:value-of>
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of select="@x"></xsl:value-of>,<xsl:value-of select="@y"></xsl:value-of>,<xsl:value-of select="@width"></xsl:value-of>,<xsl:value-of select="@height"></xsl:value-of>
					</xsl:otherwise>
				</xsl:choose>
		</xsl:attribute>
	</xsl:template>

	<xsl:template name="attr_transition">
		<xsl:for-each select="ports/ciied:Port">
			<xsl:variable name="portId" select="@id"></xsl:variable>
			<xsl:for-each select="//bg:SequenceFlow">
				<xsl:if test="@startPort=$portId">
					<transition>
						<xsl:attribute name="name">
							<xsl:value-of select="label"></xsl:value-of>
						</xsl:attribute>
  
						<xsl:variable name="startPort" select="@startPort"></xsl:variable>
						<xsl:variable name="endPort" select="@endPort"></xsl:variable>
						<xsl:variable name="parentName"
							select="name(//ciied:Port[@id=$startPort]/parent::*/parent::*/parent::*)"></xsl:variable>
						<xsl:variable name="fport" select="//ciied:Port[@id=$startPort]"></xsl:variable>
						<xsl:variable name="fromFlowEl" select="$fport/parent::*/parent::*"></xsl:variable>
						<xsl:variable name="fx" select="$fromFlowEl/@x"></xsl:variable>
						<xsl:variable name="fy" select="$fromFlowEl/@y"></xsl:variable>

						<xsl:variable name="fW" select="$fromFlowEl/@width"></xsl:variable>
						<xsl:variable name="fH" select="$fromFlowEl/@height"></xsl:variable>
						<xsl:variable name="fName" select="name($fromFlowEl)"></xsl:variable>
						<xsl:variable name="fDirX" select="$fport/@x"></xsl:variable>
						<xsl:variable name="fDirY" select="$fport/@y"></xsl:variable>
						<xsl:variable name="fHOffset" select="$fport/@horizontalOffset"></xsl:variable>
						<xsl:variable name="fVOffset" select="$fport/@verticalOffset"></xsl:variable>
			
						
						<xsl:variable name="tport" select="//ciied:Port[@id=$endPort]"></xsl:variable>
						<xsl:variable name="toFlowEl" select="$tport/parent::*/parent::*"></xsl:variable>
						<xsl:variable name="tx" select="$toFlowEl/@x"></xsl:variable>
						<xsl:variable name="ty" select="$toFlowEl/@y"></xsl:variable>
						<xsl:variable name="tW" select="$toFlowEl/@width"></xsl:variable>
						<xsl:variable name="tH" select="$toFlowEl/@height"></xsl:variable>
						<xsl:variable name="tName" select="name($toFlowEl)"></xsl:variable>
						<xsl:variable name="tDirX" select="$tport/@x"></xsl:variable>
						<xsl:variable name="tDirY" select="$tport/@y"></xsl:variable>
						<xsl:variable name="tHOffset" select="$tport/@horizontalOffset"></xsl:variable>
						<xsl:variable name="tVOffset" select="$tport/@verticalOffset"></xsl:variable>
			
						<xsl:choose>
							<xsl:when test="$parentName='bg:SubProcess'">
								<xsl:variable name="fParent"
									select="//ciied:Port[@id=$startPort]/parent::*/parent::*/parent::*"></xsl:variable>
								<xsl:variable name="tParent"
									select="//ciied:Port[@id=$endPort]/parent::*/parent::*/parent::*"></xsl:variable>
			
								<xsl:variable name="fx" select="calc:add($fx,$fParent/@x)"></xsl:variable>
								<xsl:variable name="fy" select="calc:add($fy,$fParent/@y)"></xsl:variable>
			
								<xsl:variable name="tx" select="calc:add($tx,$tParent/@x)"></xsl:variable>
								<xsl:variable name="ty" select="calc:add($ty,$tParent/@y)"></xsl:variable>
			
								<xsl:variable name="fx" select="calc:add($fx,'10')"></xsl:variable>
								<xsl:variable name="fy" select="calc:add($fy,'28')"></xsl:variable>
			
								<xsl:variable name="tx" select="calc:add($tx,'10')"></xsl:variable>
								<xsl:variable name="ty" select="calc:add($ty,'28')"></xsl:variable>
								<xsl:attribute name="g">
									<xsl:value-of select="calc:calc_jpdl($fName,$fx,$fy,$fW,$fH,$fHOffset,$fVOffset,$fDirX,$fDirY,$tName,$tx,$ty,$tW,$tH,$tHOffset,$tVOffset,$tDirX,$tDirY)" />:1,1</xsl:attribute>
								<xsl:attribute name="to">
									<xsl:value-of select="$tport/parent::*/parent::*/label"></xsl:value-of>
								</xsl:attribute>
							</xsl:when>
							<xsl:otherwise>
								<xsl:attribute name="g">
								<xsl:value-of select="calc:calc_jpdl($fName,$fx,$fy,$fW,$fH,$fHOffset,$fVOffset,$fDirX,$fDirY,$tName,$tx,$ty,$tW,$tH,$tHOffset,$tVOffset,$tDirX,$tDirY)" />:1,1</xsl:attribute>
								<xsl:attribute name="to">
									<xsl:value-of select="$tport/parent::*/parent::*/label"></xsl:value-of>
								</xsl:attribute>
							</xsl:otherwise>
						</xsl:choose>

					</transition>
				</xsl:if>
			</xsl:for-each>
		</xsl:for-each>
	</xsl:template>

</xsl:stylesheet>
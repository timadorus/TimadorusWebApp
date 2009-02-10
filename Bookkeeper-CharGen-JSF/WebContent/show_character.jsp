<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%> 

<f:view>
<%@ include file="nav/header.jspf" %>   
	<rich:tabPanel switchType="ajax" selectedTab="skills_tab">
        <rich:tab id="base_tab" label="Base">
			<%@ include file="divs/stats_div.jspf" %>   
     		<%@ include file="divs/base_info_div.jspf" %>   
        </rich:tab>
        <rich:tab id="skills_tab" label="Skills">
     		<%@ include file="divs/skills_div.jspf" %>   
			<h:form id="addSkillForm">
				<a4j:outputPanel layout="block">
	            	<h:outputText value="Add Skill:"/> 
					<h:inputText value="#{statsbean.addPickSkillName}" id="skillName">
						<a4j:support event="onkeyup" reRender="resultBlock" />
					</h:inputText>
	   				<rich:suggestionbox for="skillName" 
	   					suggestionAction="#{skilldatabean.skillNames}" 
	                    var="suggest" height="250" width="350">
	        			<h:column>
	            			<h:outputText value="#{suggest}"/> 
						</h:column>
					</rich:suggestionbox>
					<h:inputText value="#{statsbean.addPickSubSkillName}" id="subSkillName">
					</h:inputText>
	   				<rich:suggestionbox for="subSkillName" 
	   					suggestionAction="#{statsbean.subSkillNames}" 
	                    var="suggest" height="250" width="350">
	        			<h:column>
	            			<h:outputText value="#{suggest}"/> 
						</h:column>
					</rich:suggestionbox>
					<a4j:outputPanel layout="block" id="resultBlock">
						<h:outputText value="Cost:"/> 
						<h:inputText id="cost_in" value="#{statsbean.addPickCost}"/>
						<a4j:commandButton id="add_button" disabled="#{not statsbean.skillComboIsValid}" value="Add Pick" action="#{statsbean.addSkill}"/>
						<h:outputText id="addSkillMessage" value="Approved Text: #{statsbean.addSkillMessage}" />
					</a4j:outputPanel>
				</a4j:outputPanel>
			</h:form>
        </rich:tab>
        <rich:tab id="history_tab" label="Skill History">
     			<%@ include file="divs/skills_history_div.jspf" %>   
        </rich:tab>
        <rich:tab id="equipment_tab" label="Equipment">
        <h:outputText value="The equipment will be here."/>
        </rich:tab>
    </rich:tabPanel>
<%@ include file="nav/footer.jspf" %>   
</f:view>
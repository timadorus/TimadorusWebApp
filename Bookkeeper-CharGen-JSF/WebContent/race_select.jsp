<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>

<f:view>
<%@ include file="nav/header.jspf" %>   
   <div align="center">
     <h:outputText styleClass="portlet-font" value="Select Race"/>
      <br/>
      <h:form id="race_class_select_form">
     	<h:selectOneMenu value="#{statsbean.raceName}">
     		<f:selectItems value="#{statsbean.raceNames}" />
      	</h:selectOneMenu>
     	<h:selectOneMenu value="#{statsbean.className}">
     		<f:selectItems value="#{statsbean.classNames}" />
      	</h:selectOneMenu>
        <h:commandButton action="#{statsbean.setClassRace}" id="next_step_button" value="Select"/>
      </h:form>
      <br/>
         <%@ include file="divs/stats_div.jspf" %>
      <br/>

   </div>
<%@ include file="nav/footer.jspf" %>   
</f:view>
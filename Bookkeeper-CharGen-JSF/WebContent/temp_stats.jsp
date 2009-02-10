<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>

<f:view>

<%@ include file="nav/header.jspf" %>   
   <div align="center">
     <h:outputText styleClass="portlet-font" value="Assign Temporary Stats"/>
     <br/>
      <h:form id="temp_stats_form">
         <h:panelGrid columns="2">
            <h:outputText styleClass="portlet-font" value="Stat"/>
            <h:outputText styleClass="portlet-font" value="Temp-Roll"/>
            
            <h:outputText styleClass="portlet-font" value="CO:"/>
            <h:inputText value="#{statsbean.temp.Co}" size="5" required="true"/>
            <h:outputText styleClass="portlet-font" value="AG:"/>
            <h:inputText value="#{statsbean.temp.Ag}" size="5" required="true"/>
            <h:outputText styleClass="portlet-font" value="SD:"/>
            <h:inputText value="#{statsbean.temp.Sd}" size="5" required="true"/>
            <h:outputText styleClass="portlet-font" value="ME:"/>
            <h:inputText value="#{statsbean.temp.Me}" size="5" required="true"/>
            <h:outputText styleClass="portlet-font" value="RE:"/>
            <h:inputText value="#{statsbean.temp.Re}" size="5" required="true"/>
            <h:outputText styleClass="portlet-font" value="LU:"/>
            <h:inputText value="#{statsbean.temp.Lu}" size="5" required="true"/>
            
            <h:outputText styleClass="portlet-font" value=" "/>
            <h:outputText styleClass="portlet-font" value=" "/>
            
            <h:outputText styleClass="portlet-font" value="ST:"/>
            <h:inputText value="#{statsbean.temp.St}" size="5" required="true"/>
            <h:outputText styleClass="portlet-font" value="QU:"/>
            <h:inputText value="#{statsbean.temp.Qu}" size="5" required="true"/>
            <h:outputText styleClass="portlet-font" value="EM:"/>
            <h:inputText value="#{statsbean.temp.Em}" size="5" required="true"/>
            <h:outputText styleClass="portlet-font" value="IN:"/>
            <h:inputText value="#{statsbean.temp.In}" size="5" required="true"/>
            <h:outputText styleClass="portlet-font" value="PR:"/>
            <h:inputText value="#{statsbean.temp.Pr}" size="5" required="true"/>
            
            <h:outputText styleClass="portlet-font" value=" "/>
            <h:outputText styleClass="portlet-font" value=" "/>
            
            <h:outputText styleClass="portlet-font" value="AP:"/>
            <h:inputText value="#{statsbean.temp.Ap}" size="5" required="true"/>
         </h:panelGrid>
         <br/>
         <h:commandButton action="#{statsbean.resolveTempStep}" id="resolve_step_button" value="Next Step: Pots"/>
      </h:form>
         <br/>
      <h:form id="random_stats_form">
         <h:commandButton action="#{statsbean.rollTempStep}" id="roll_step_button" value="Random Values"/>
      </h:form>

   </div>
<%@ include file="nav/footer.jspf" %>   

</f:view>

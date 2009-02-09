<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>

<f:view>
<%@ include file="nav/header.jspf" %>   
   <div align="center">
     <h:outputText styleClass="portlet-font" value="Assign Potential Stats"/>
     <br/>
      <h:form id="temp_stats_form">
         <h:panelGrid columns="3">
            <h:outputText styleClass="portlet-font" value="Stat"/>
            <h:outputText styleClass="portlet-font" value="Temp"/>
            <h:outputText styleClass="portlet-font" value="Pot-Roll"/>
            
            <h:outputText styleClass="portlet-font" value="CO:"/>
            <h:outputText styleClass="portlet-font" value="#{statsbean.temp.Co}"/>
            <h:inputText value="#{statsbean.potRoll.Co}" size="5" required="true"/>
            <h:outputText styleClass="portlet-font" value="AG:"/>
            <h:outputText styleClass="portlet-font" value="#{statsbean.temp.Ag}"/>
            <h:inputText value="#{statsbean.potRoll.Ag}" size="5" required="true"/>
            <h:outputText styleClass="portlet-font" value="SD:"/>
            <h:outputText styleClass="portlet-font" value="#{statsbean.temp.Sd}"/>
            <h:inputText value="#{statsbean.potRoll.Sd}" size="5" required="true"/>
            <h:outputText styleClass="portlet-font" value="ME:"/>
            <h:outputText styleClass="portlet-font" value="#{statsbean.temp.Me}"/>
            <h:inputText value="#{statsbean.potRoll.Me}" size="5" required="true"/>
            <h:outputText styleClass="portlet-font" value="RE:"/>
            <h:outputText styleClass="portlet-font" value="#{statsbean.temp.Re}"/>
            <h:inputText value="#{statsbean.potRoll.Re}" size="5" required="true"/>
            <h:outputText styleClass="portlet-font" value="LU:"/>
            <h:outputText styleClass="portlet-font" value="#{statsbean.temp.Lu}"/>
            <h:inputText value="#{statsbean.potRoll.Lu}" size="5" required="true"/>
            
            <h:outputText styleClass="portlet-font" value=" "/>
            <h:outputText styleClass="portlet-font" value=" "/>
            <h:outputText styleClass="portlet-font" value=" "/>
            
            <h:outputText styleClass="portlet-font" value="ST:"/>
            <h:outputText styleClass="portlet-font" value="#{statsbean.temp.St}"/>
            <h:inputText value="#{statsbean.potRoll.St}" size="5" required="true"/>
            <h:outputText styleClass="portlet-font" value="QU:"/>
            <h:outputText styleClass="portlet-font" value="#{statsbean.temp.Qu}"/>
            <h:inputText value="#{statsbean.potRoll.Qu}" size="5" required="true"/>
            <h:outputText styleClass="portlet-font" value="EM:"/>
            <h:outputText styleClass="portlet-font" value="#{statsbean.temp.Em}"/>
            <h:inputText value="#{statsbean.potRoll.Em}" size="5" required="true"/>
            <h:outputText styleClass="portlet-font" value="IN:"/>
            <h:outputText styleClass="portlet-font" value="#{statsbean.temp.In}"/>
            <h:inputText value="#{statsbean.potRoll.In}" size="5" required="true"/>
            <h:outputText styleClass="portlet-font" value="PR:"/>
            <h:outputText styleClass="portlet-font" value="#{statsbean.temp.Pr}"/>
            <h:inputText value="#{statsbean.potRoll.Pr}" size="5" required="true"/>
            
            <h:outputText styleClass="portlet-font" value=" "/>
            <h:outputText styleClass="portlet-font" value=" "/>
            <h:outputText styleClass="portlet-font" value=" "/>
            
            <h:outputText styleClass="portlet-font" value="AP:"/>
            <h:outputText styleClass="portlet-font" value="#{statsbean.temp.Ap}"/>
            <h:outputText styleClass="portlet-font" value=" "/>
         </h:panelGrid>
         <br/>
         <h:commandButton action="#{statsbean.resolvePotStep}" id="resolve_step_button" value="Next Step: Pots"/>
      </h:form>
         <br/>
      <h:form id="random_stats_form">
         <h:commandButton action="#{statsbean.rollPotStep}" id="roll_step_button" value="Random Values"/>
      </h:form>

   </div>

<%@ include file="nav/footer.jspf" %>   
</f:view>
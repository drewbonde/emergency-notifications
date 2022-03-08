<c:set var="req" value="${pageContext.request}" />
<c:set
  var="urlBase"
  value="${req.scheme}://${req.serverName}:${req.localPort}${req.contextPath}"
/>
<script>
  var token = `${bearer.getEncryptedToken()}`;
  var IS_DEMO = false;
</script>
<div id="emergency-notifications-soffit">An error occurred</div>
<script
  src="${pageContext.request.contextPath}/js/main.js"
  type="text/javascript"
></script>
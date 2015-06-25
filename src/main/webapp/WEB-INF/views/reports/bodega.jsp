<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en-us">
<head>
<meta charset="utf-8">
<!--<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">-->

<title>Papeles Nacionales</title>
<meta name="description" content="">
<meta name="author" content="">

<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

<!-- Basic Styles -->
<link rel="stylesheet" type="text/css" media="screen"
	href="<c:url value="/resources/css/bootstrap.min.css"/>">
<link rel="stylesheet" type="text/css" media="screen"
	href="<c:url value="../resources/css/font-awesome.min.css"/>">

<!-- SmartAdmin Styles : Please note (smartadmin-production.css) was created using LESS variables -->
<link rel="stylesheet" type="text/css" media="screen"
	href="<c:url value="/resources/css/smartadmin-production.min.css" />">
<link rel="stylesheet" type="text/css" media="screen"
	href="<c:url value="/resources/css/smartadmin-skins.min.css" />">

<!-- SmartAdmin RTL Support is under construction
			 This RTL CSS will be released in version 1.5
		<link rel="stylesheet" type="text/css" media="screen" href="css/smartadmin-rtl.min.css"> -->

<!-- We recommend you use "your_style.css" to override SmartAdmin
		     specific styles this will also ensure you retrain your customization with each SmartAdmin update.
		<link rel="stylesheet" type="text/css" media="screen" href="css/your_style.css"> -->

<!-- Demo purpose only: goes with demo.js, you can delete this css when designing your own WebApp -->
<link rel="stylesheet" type="text/css" media="screen"
	href="<c:url value="/resources/css/demo.min.css" />">

<!-- FAVICONS -->
<link rel="shortcut icon"
	href="<c:url value="/resources/img/favicon/favicon.ico" />"
	type="image/x-icon">
<link rel="icon"
	href="<c:url value="/resources/img/favicon/favicon.ico" />"
	type="image/x-icon">

<!-- GOOGLE FONT -->
<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,700italic,300,400,700">

<!-- Specifying a Webpage Icon for Web Clip 
			 Ref: https://developer.apple.com/library/ios/documentation/AppleApplications/Reference/SafariWebContent/ConfiguringWebApplications/ConfiguringWebApplications.html -->
<link rel="apple-touch-icon"
	href="<c:url value="/resources/img/splash/sptouch-icon-iphone.png" />">
<link rel="apple-touch-icon" sizes="76x76"
	href="<c:url value="/resources/img/splash/touch-icon-ipad.png" />">
<link rel="apple-touch-icon" sizes="120x120"
	href="<c:url value="/resources/img/splash/touch-icon-iphone-retina.png" />">
<link rel="apple-touch-icon" sizes="152x152"
	href="<c:url value="/resources/img/splash/touch-icon-ipad-retina.png" />">

<!-- iOS web-app metas : hides Safari UI Components and Changes Status Bar Appearance -->
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<!-- Startup image for web apps -->
<link rel="apple-touch-startup-image"
	href="<c:url value="/resources/img/splash/ipad-landscape.png" />"
	media="screen and (min-device-width: 481px) and (max-device-width: 1024px) and (orientation:landscape)">
<link rel="apple-touch-startup-image"
	href="<c:url value="/resources/img/splash/ipad-portrait.png" />"
	media="screen and (min-device-width: 481px) and (max-device-width: 1024px) and (orientation:portrait)">
<link rel="apple-touch-startup-image"
	href="<c:url value="/resources/img/splash/iphone.png" />"
	media="screen and (max-device-width: 320px)">
<style type="text/css">
tr:last-child {
	color: #ff0000;
	font-weight: bold;
}
</style>
</head>
<body class="">

	<!-- possible classes: minified, fixed-ribbon, fixed-header, fixed-width-->

	<!-- HEADER -->
	<header id="header">
		<div id="logo-group">
			<!-- PLACE YOUR LOGO HERE -->
			<span id="logo"> <img
				src="<c:url value="/resources/img/logo.png"/>" alt="SmartAdmin">
			</span>
			<!-- END LOGO PLACEHOLDER -->
		</div>

		<!-- pulled right: nav area -->
		<div class="pull-right">

			<!-- collapse menu button 
				<div id="hide-menu" class="btn-header pull-right">
					<span> <a href="javascript:void(0);" data-action="toggleMenu" title="Collapse Menu"><i class="fa fa-reorder"></i></a> </span>
				</div>
				 end collapse menu -->

			<!-- #MOBILE -->
			<!-- Top menu profile link : this shows only when top menu is active -->
			<ul id="mobile-profile-img"
				class="header-dropdown-list hidden-xs padding-5">
				<li class="">
					<ul class="dropdown-menu pull-right">
						<li><a href="login.html"
							class="padding-10 padding-top-5 padding-bottom-5"
							data-action="userLogout"><i class="fa fa-sign-out fa-lg"></i>
								<strong><u>S</u>alir</strong></a></li>
					</ul>
				</li>
			</ul>

			<a href="mostrar" class="btn btn-labeled btn-warning"> <span
				class="btn-label"><i class="glyphicon glyphicon-bookmark"></i></span>Inicio
			</a> <a href="retornar" class="btn btn-labeled btn-default"> <span
				class="btn-label"><i class="glyphicon glyphicon-chevron-left"></i></span>Regresar
			</a> <a href="salir" class="btn btn-labeled btn-danger"> <span
				class="btn-label"><i class="fa fa-sign-out"></i></span>Salir
			</a>

			<!-- end logout button -->

		</div>


		<!-- end pulled right: nav area -->

	</header>
	<!-- END HEADER -->

	<!-- MAIN CONTENT -->
	<div id="content">

		<!-- widget grid -->
		<section id="widget-grid">

			<!-- row -->
			<div class="row">

				<!-- NEW WIDGET START -->
				<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">

					<!-- Widget ID (each widget will need unique ID)-->
					<div class="jarviswidget jarviswidget-color-darken far"
						id="wid-id-0" data-widget-editbutton="false">

						<!-- widget div-->
						<div>

							<!-- widget content -->
							<div class="widget-body no-padding">

								<table class="table table-striped table-bordered" width="100%">
									<thead>
										<tr>
											<td colspan="16" align="center"><c:out
													value="${mensaje}" /></td>
										</tr>
										<tr>
											<th rowspan="2" style="text-align: center; color: blue;">Bodega</th>
											<c:choose>
												<c:when test="${ user_inicio.campover == 'ord'}">
													<th rowspan="2" style="text-align: center; color: blue;">Val.
														Ordenado</th>
												</c:when>
												<c:otherwise>
													<!-- <th rowspan="2" style="text-align: center; color: blue;">Saldo
												Proveedor</th>  -->

													<th rowspan="2" style="text-align: center; color: blue;">Valor
														Recibido</th>
												</c:otherwise>
											</c:choose>
											<!-- <th rowspan="2" style="text-align: center; color: blue;">Valor
												Recibido</th>  -->

											<th colspan="3" style="text-align: center; color: blue;">Cantidades</th>

											<c:if test="${o == 0 && r == 0}">
												<th rowspan="2" style="text-align: center; color: blue;">O/C</th>
											</c:if>
											<c:if test="${r == 0 && o == 0}">
												<th rowspan="2" style="text-align: center; color: blue;">R/Q</th>
											</c:if>
											<c:if test="${ user_inicio.tipoUsuario == 2 }">
												<c:if test="${c == 0}">
													<th rowspan="2" style="text-align: center; color: blue;">Comprador</th>
												</c:if>
											</c:if>
											<c:if test="${p == 0}">
												<th rowspan="2" style="text-align: center; color: blue;">Proveedor</th>
											</c:if>
											<c:if test="${i == 0}">
												<th rowspan="2" style="text-align: center; color: blue;">Items</th>
											</c:if>
											<c:if test="${q == 0}">
												<th rowspan="2" style="text-align: center; color: blue;">Clase</th>
											</c:if>
											<c:if test="${k == 0}">
												<th rowspan="2" style="text-align: center; color: blue;">Cent.
													Costo</th>
											</c:if>
											<c:if test="${e == 0}">
												<th rowspan="2" style="text-align: center; color: blue;">Estado</th>
											</c:if>
										</tr>
										<tr>
											<c:choose>
												<c:when test="${ user_inicio.campover == 'ord'}">
													<th style="text-align: center; color: blue;">Ordenadas</th>
												</c:when>
												<c:otherwise>
													<th style="text-align: center; color: blue;">Recibidas</th>
												</c:otherwise>
											</c:choose>
											<th style="text-align: center; color: blue;">Rechazadas</th>
											<th style="text-align: center; color: blue;">Pendiente a
												la Fecha</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${listcomp}" var="compp"
											varStatus="loopCounter">
											<tr>
												<td><c:out value="${compp.plnon}" /></td>
												<c:choose>
													<c:when test="${ user_inicio.campover == 'ord'}">
														<td style="text-align: right">$<fmt:formatNumber
																value="${compp.pvalbo}" type="number" /></td>
													</c:when>
													<c:otherwise>
														<td style="text-align: right">$<fmt:formatNumber
																value="${compp.pvalbd}" type="number" /></td>
													</c:otherwise>
												</c:choose>
												<c:choose>
													<c:when test="${ user_inicio.campover == 'ord'}">
														<td style="text-align: right"><fmt:formatNumber
																value="${compp.pqori}" type="number" /></td>
													</c:when>
													<c:otherwise>
														<td style="text-align: right"><fmt:formatNumber
																value="${compp.pqtyd}" type="number" /></td>
													</c:otherwise>
												</c:choose>
												<td style="text-align: right"><fmt:formatNumber
														value="${compp.pqtyr}" type="number" /></td>
												<td style="text-align: right"><fmt:formatNumber
														value="${compp.pqtyp}" type="number" /></td>
												<fmt:setLocale value="en_US" />

												<!-- <td style="text-align: right">$<fmt:formatNumber
														value="${compp.ppreac}" type="number" /></td>  -->

												<!-- <td style="text-align: right">$<fmt:formatNumber
														value="${compp.pvalpo}" type="number" /></td>  -->


												<c:if test="${o == 0 && r == 0}">
													<td align="center"><form:form method="POST"
															action="bod" ModelAttribute="compra" commandName="compra">
															<form:input path="plocal" type="hidden"
																value="${compp.plocal}" />
															<form:input path="plnon" type="hidden"
																value="${compp.plnon}" />
															<button type="submit" Value="oc" name="next"
																style="border-style: none; background-color: Transparent; background-repeat: no-repeat; border: none; cursor: pointer; overflow: hidden;">
																<img width="20" height="20"
																	src="<c:url value="/resources/img/adm/ordencompra.png" />">
															</button>
														</form:form></td>
												</c:if>
												<c:if test="${r == 0 && o == 0}">
													<td align="center"><form:form method="POST"
															action="bod" ModelAttribute="compra" commandName="compra">
															<form:input path="plocal" type="hidden"
																value="${compp.plocal}" />
															<form:input path="plnon" type="hidden"
																value="${compp.plnon}" />
															<button type="submit" Value="rq" name="next"
																style="border-style: none; background-color: Transparent; background-repeat: no-repeat; border: none; cursor: pointer; overflow: hidden;">
																<img width="20" height="20"
																	src="<c:url value="/resources/img/adm/requisicion.png" />">
															</button>
														</form:form></td>
												</c:if>
												<c:if test="${ user_inicio.tipoUsuario == 2 }">
													<c:if test="${c == 0}">
														<td align="center"><form:form method="POST"
																action="bod" ModelAttribute="compra"
																commandName="compra">
																<form:input path="plocal" type="hidden"
																	value="${compp.plocal}" />
																<form:input path="plnon" type="hidden"
																	value="${compp.plnon}" />
																<button type="submit" Value="compra" name="next"
																	style="border-style: none; background-color: Transparent; background-repeat: no-repeat; border: none; cursor: pointer; overflow: hidden;">
																	<img width="20" height="20"
																		src="<c:url value="/resources/img/adm/comprador.png" />">
																</button>
															</form:form></td>
													</c:if>
												</c:if>
												<c:if test="${p == 0}">
													<td align="center"><form:form method="POST"
															action="bod" ModelAttribute="compra" commandName="compra">
															<form:input path="plocal" type="hidden"
																value="${compp.plocal}" />
															<form:input path="plnon" type="hidden"
																value="${compp.plnon}" />
															<button type="submit" Value="prove" name="next"
																style="border-style: none; background-color: Transparent; background-repeat: no-repeat; border: none; cursor: pointer; overflow: hidden;">
																<img width="20" height="20"
																	src="<c:url value="/resources/img/adm/proveedor.png" />">
															</button>
														</form:form></td>
												</c:if>
												<c:if test="${i == 0}">
													<td align="center"><form:form method="POST"
															action="bod" ModelAttribute="compra" commandName="compra">
															<form:input path="plocal" type="hidden"
																value="${compp.plocal}" />
															<form:input path="plnon" type="hidden"
																value="${compp.plnon}" />
															<button type="submit" Value="ite" name="next"
																style="border-style: none; background-color: Transparent; background-repeat: no-repeat; border: none; cursor: pointer; overflow: hidden;">
																<img width="20" height="20"
																	src="<c:url value="/resources/img/adm/producto.png" />">
															</button>
														</form:form></td>
												</c:if>
												<c:if test="${q == 0}">
													<td align="center"><form:form method="POST"
															action="bod" ModelAttribute="compra" commandName="compra">
															<form:input path="plocal" type="hidden"
																value="${compp.plocal}" />
															<form:input path="plnon" type="hidden"
																value="${compp.plnon}" />
															<button type="submit" Value="clas" name="next"
																style="border-style: none; background-color: Transparent; background-repeat: no-repeat; border: none; cursor: pointer; overflow: hidden;">
																<img width="20" height="20"
																	src="<c:url value="/resources/img/adm/tipoproducto.png" />">
															</button>
														</form:form></td>
												</c:if>
												<c:if test="${k == 0}">
													<td align="center"><form:form method="POST"
															action="bod" ModelAttribute="compra" commandName="compra">
															<form:input path="plocal" type="hidden"
																value="${compp.plocal}" />
															<form:input path="plnon" type="hidden"
																value="${compp.plnon}" />
															<button type="submit" Value="centr" name="next"
																style="border-style: none; background-color: Transparent; background-repeat: no-repeat; border: none; cursor: pointer; overflow: hidden;">
																<img width="20" height="20"
																	src="<c:url value="/resources/img/adm/centrocosto.png" />">
															</button>
														</form:form></td>
												</c:if>
												<c:if test="${e == 0}">
													<td align="center"><form:form method="POST"
															action="bod" ModelAttribute="compra" commandName="compra">
															<form:input path="plocal" type="hidden"
																value="${compp.plocal}" />
															<form:input path="plnon" type="hidden"
																value="${compp.plnon}" />
															<button type="submit" Value="est" name="next"
																style="border-style: none; background-color: Transparent; background-repeat: no-repeat; border: none; cursor: pointer; overflow: hidden;">
																<img width="20" height="20"
																	src="<c:url value="/resources/img/adm/estado.png" />">
															</button>
														</form:form></td>
												</c:if>
											</tr>
										</c:forEach>

									</tbody>
								</table>
							</div>
							<div style="float: right;">
								<strong>Usuario: </strong>${usuarioactuall}</div>
							<!-- end widget content -->

						</div>
						<!-- end widget div -->

					</div>
					<!-- end widget -->

				</article>
				<!-- WIDGET END -->

			</div>

			<!-- end row -->

			<!-- end row -->

		</section>
		<!-- end widget grid -->

	</div>
	<!-- END MAIN CONTENT -->

	<!--
	<div class="page-footer">
		<div class="row">
			<div class="col-xs-12 col-sm-6">
				<span class="txt-color-white">Flash Ventas - 2014</span>
			</div>
		</div>
	</div>
	 END PAGE FOOTER -->


	<!--================================================== -->

	<!-- PACE LOADER - turn this on if you want ajax loading to show (caution: uses lots of memory on iDevices)-->
	<script data-pace-options='{ "restartOnRequestAfter": true }'
		src="<c:url value="/resources/js/plugin/pace/pace.min.js"/>"></script>

	<!-- Link to Google CDN's jQuery + jQueryUI; fall back to local -->
	<script
		src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
	<script>
		if (!window.jQuery) {
			document
					.write('<script src="<c:url value="/resources/js/libs/jquery-2.0.2.min.js"/>""><\/script>');
		}
	</script>

	<script
		src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
	<script>
		if (!window.jQuery.ui) {
			document
					.write('<script src="<c:url value="/resources/js/libs/jquery-ui-1.10.3.min.js"/>"><\/script>');
		}
	</script>

	<!-- IMPORTANT: APP CONFIG -->
	<script src="<c:url value="/resources/js/app.config.js"/>"></script>

	<!-- JS TOUCH : include this plugin for mobile drag / drop touch events-->
	<script
		src="<c:url value="/resources/js/plugin/jquery-touch/jquery.ui.touch-punch.min.js"/>"></script>

	<!-- BOOTSTRAP JS -->
	<script src="<c:url value="/resources/js/bootstrap/bootstrap.min.js"/>"></script>

	<!-- CUSTOM NOTIFICATION -->
	<script
		src="<c:url value="/resources/js/notification/SmartNotification.min.js"/>"></script>

	<!-- JARVIS WIDGETS -->
	<script
		src="<c:url value="/resources/js/smartwidgets/jarvis.widget.min.js"/>"></script>

	<!-- EASY PIE CHARTS -->
	<script
		src="<c:url value="/resources/js/plugin/easy-pie-chart/jquery.easy-pie-chart.min.js"/>"></script>

	<!-- SPARKLINES -->
	<script
		src="<c:url value="/resources/js/plugin/sparkline/jquery.sparkline.min.js"/>"></script>

	<!-- JQUERY VALIDATE -->
	<script
		src="<c:url value="/resources/vjs/plugin/jquery-validate/jquery.validate.min.js"/>"></script>

	<!-- JQUERY MASKED INPUT -->
	<script
		src="<c:url value="/resources/js/plugin/masked-input/jquery.maskedinput.min.js"/>"></script>

	<!-- JQUERY SELECT2 INPUT -->
	<script
		src="<c:url value="/resources/js/plugin/select2/select2.min.js"/>"></script>

	<!-- JQUERY UI + Bootstrap Slider -->
	<script
		src="<c:url value="/resources/js/plugin/bootstrap-slider/bootstrap-slider.min.js"/>"></script>

	<!-- browser msie issue fix -->
	<script
		src="<c:url value="/resources/js/plugin/msie-fix/jquery.mb.browser.min.js"/>"></script>

	<!-- FastClick: For mobile devices -->
	<script
		src="<c:url value="/resources/js/plugin/fastclick/fastclick.min.js"/>"></script>

	<!--[if IE 8]>

		<h1>Your browser is out of date, please update your browser by going to www.microsoft.com/download</h1>

		<![endif]-->

	<!-- Demo purpose only -->
	<script src="<c:url value="/resources/js/demo.min.js"/>"></script>

	<!-- MAIN APP JS FILE -->
	<script src="<c:url value="/resources/js/app.min.js"/>"></script>

	<!-- ENHANCEMENT PLUGINS : NOT A REQUIREMENT -->
	<!-- Voice command : plugin -->
	<script src="<c:url value="/resources/js/speech/voicecommand.min.js"/>"></script>

	<!-- PAGE RELATED PLUGIN(S) -->
	<script
		src="<c:url value="/resources/js/plugin/datatables/jquery.dataTables.min.js"/>"></script>
	<script
		src="<c:url value="/resources/js/plugin/datatables/dataTables.colVis.min.js"/>"></script>
	<script
		src="<c:url value="/resources/js/plugin/datatables/dataTables.tableTools.min.js"/>"></script>
	<script
		src="<c:url value="/resources/js/plugin/datatables/dataTables.bootstrap.min.js"/>"></script>
	<script
		src="<c:url value="/resources/js/plugin/datatable-responsive/datatables.responsive.min.js"/>"></script>

	<script type="text/javascript">
		// DO NOT REMOVE : GLOBAL FUNCTIONS!

		$(document)
				.ready(
						function() {

							pageSetUp();

							/* // DOM Position key index //
							
							l - Length changing (dropdown)
							f - Filtering input (search)
							t - The Table! (datatable)
							i - Information (records)
							p - Pagination (paging)
							r - pRocessing 
							< and > - div elements
							<"#id" and > - div with an id
							<"class" and > - div with a class
							<"#id.class" and > - div with an id and class
							
							Also see: http://legacy.datatables.net/usage/features
							 */

							/* BASIC ;*/
							var responsiveHelper_dt_basic = undefined;
							var responsiveHelper_datatable_fixed_column = undefined;
							var responsiveHelper_datatable_col_reorder = undefined;
							var responsiveHelper_datatable_tabletools = undefined;

							var breakpointDefinition = {
								tablet : 1024,
								phone : 480
							};

							$('#dt_basic')
									.dataTable(
											{
												"sDom" : "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-12 hidden-xs'l>r>"
														+ "t"
														+ "<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-xs-12 col-sm-6'p>>",
												"autoWidth" : true,
												"preDrawCallback" : function() {
													// Initialize the responsive datatables helper once.
													if (!responsiveHelper_dt_basic) {
														responsiveHelper_dt_basic = new ResponsiveDatatablesHelper(
																$('#dt_basic'),
																breakpointDefinition);
													}
												},
												"rowCallback" : function(nRow) {
													responsiveHelper_dt_basic
															.createExpandIcon(nRow);
												},
												"drawCallback" : function(
														oSettings) {
													responsiveHelper_dt_basic
															.respond();
												}
											});

							/* END BASIC */

							/* COLUMN FILTER  */
							var otable = $('#datatable_fixed_column')
									.DataTable(
											{
												//"bFilter": false,
												//"bInfo": false,
												//"bLengthChange": false
												//"bAutoWidth": false,
												//"bPaginate": false,
												//"bStateSave": true // saves sort state using localStorage
												"sDom" : "<'dt-toolbar'<'col-xs-12 col-sm-6 hidden-xs'f><'col-sm-6 col-xs-12 hidden-xs'<'toolbar'>>r>"
														+ "t"
														+ "<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-xs-12 col-sm-6'p>>",
												"autoWidth" : true,
												"preDrawCallback" : function() {
													// Initialize the responsive datatables helper once.
													if (!responsiveHelper_datatable_fixed_column) {
														responsiveHelper_datatable_fixed_column = new ResponsiveDatatablesHelper(
																$('#datatable_fixed_column'),
																breakpointDefinition);
													}
												},
												"rowCallback" : function(nRow) {
													responsiveHelper_datatable_fixed_column
															.createExpandIcon(nRow);
												},
												"drawCallback" : function(
														oSettings) {
													responsiveHelper_datatable_fixed_column
															.respond();
												}

											});

							// custom toolbar
							$("div.toolbar")
									.html(
											'<div class="text-right"><img src="<c:url value="/resources/img/logo.png"/>" alt="SmartAdmin" style="width: 111px; margin-top: 3px; margin-right: 10px;"></div>');

							// Apply the filter
							$(
									"#datatable_fixed_column thead th input[type=text]")
									.on(
											'keyup change',
											function() {

												otable.column(
														$(this).parent()
																.index()
																+ ':visible')
														.search(this.value)
														.draw();

											});
							/* END COLUMN FILTER */

							/* COLUMN SHOW - HIDE */
							$('#datatable_col_reorder')
									.dataTable(
											{
												"sDom" : "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-6 hidden-xs'C>r>"
														+ "t"
														+ "<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-sm-6 col-xs-12'p>>",
												"autoWidth" : true,
												"preDrawCallback" : function() {
													// Initialize the responsive datatables helper once.
													if (!responsiveHelper_datatable_col_reorder) {
														responsiveHelper_datatable_col_reorder = new ResponsiveDatatablesHelper(
																$('#datatable_col_reorder'),
																breakpointDefinition);
													}
												},
												"rowCallback" : function(nRow) {
													responsiveHelper_datatable_col_reorder
															.createExpandIcon(nRow);
												},
												"drawCallback" : function(
														oSettings) {
													responsiveHelper_datatable_col_reorder
															.respond();
												}
											});

							/* END COLUMN SHOW - HIDE */

							/* TABLETOOLS */
							$('#datatable_tabletools')
									.dataTable(
											{

												// Tabletools options: 
												//   https://datatables.net/extensions/tabletools/button_options
												"sDom" : "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-6 hidden-xs'T>r>"
														+ "t"
														+ "<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-sm-6 col-xs-12'p>>",
												"oTableTools" : {
													"aButtons" : [
															"copy",
															"csv",
															"xls",
															{
																"sExtends" : "pdf",
																"sTitle" : "SmartAdmin_PDF",
																"sPdfMessage" : "SmartAdmin PDF Export",
																"sPdfSize" : "letter"
															},
															{
																"sExtends" : "print",
																"sMessage" : "Generated by SmartAdmin <i>(press Esc to close)</i>"
															} ],
													"sSwfPath" : "js/plugin/datatables/swf/copy_csv_xls_pdf.swf"
												},
												"autoWidth" : true,
												"preDrawCallback" : function() {
													// Initialize the responsive datatables helper once.
													if (!responsiveHelper_datatable_tabletools) {
														responsiveHelper_datatable_tabletools = new ResponsiveDatatablesHelper(
																$('#datatable_tabletools'),
																breakpointDefinition);
													}
												},
												"rowCallback" : function(nRow) {
													responsiveHelper_datatable_tabletools
															.createExpandIcon(nRow);
												},
												"drawCallback" : function(
														oSettings) {
													responsiveHelper_datatable_tabletools
															.respond();
												}
											});

							/* END TABLETOOLS */

						})

		$(function() {
			$("table").stickyTableHeaders();
		});
		/*! Copyright (c) 2011 by Jonas Mosbech - https://github.com/jmosbech/StickyTableHeaders
		    MIT license info: https://github.com/jmosbech/StickyTableHeaders/blob/master/license.txt */

		;
		(function($, window, undefined) {
			'use strict';

			var name = 'stickyTableHeaders';
			var defaults = {
				fixedOffset : 0
			};

			function Plugin(el, options) {
				// To avoid scope issues, use 'base' instead of 'this'
				// to reference this class from internal events and functions.
				var base = this;

				// Access to jQuery and DOM versions of element
				base.$el = $(el);
				base.el = el;

				// Listen for destroyed, call teardown
				base.$el.bind('destroyed', $.proxy(base.teardown, base));

				// Cache DOM refs for performance reasons
				base.$window = $(window);
				base.$clonedHeader = null;
				base.$originalHeader = null;

				// Keep track of state
				base.isSticky = false;
				base.leftOffset = null;
				base.topOffset = null;

				base.init = function() {
					base.options = $.extend({}, defaults, options);

					base.$el
							.each(function() {
								var $this = $(this);

								// remove padding on <table> to fix issue #7
								$this.css('padding', 0);

								base.$originalHeader = $('thead:first', this);
								base.$clonedHeader = base.$originalHeader
										.clone();

								base.$clonedHeader
										.addClass('tableFloatingHeader');
								base.$clonedHeader.css('display', 'none');

								base.$originalHeader
										.addClass('tableFloatingHeaderOriginal');

								base.$originalHeader.after(base.$clonedHeader);

								base.$printStyle = $('<style type="text/css" media="print">'
										+ '.tableFloatingHeader{display:none !important;}'
										+ '.tableFloatingHeaderOriginal{position:static !important;}'
										+ '</style>');
								$('head').append(base.$printStyle);
							});

					base.updateWidth();
					base.toggleHeaders();

					base.bind();
				};

				base.destroy = function() {
					base.$el.unbind('destroyed', base.teardown);
					base.teardown();
				};

				base.teardown = function() {
					if (base.isSticky) {
						base.$originalHeader.css('position', 'static');
					}
					$.removeData(base.el, 'plugin_' + name);
					base.unbind();

					base.$clonedHeader.remove();
					base.$originalHeader
							.removeClass('tableFloatingHeaderOriginal');
					base.$originalHeader.css('visibility', 'visible');
					base.$printStyle.remove();

					base.el = null;
					base.$el = null;
				};

				base.bind = function() {
					base.$window.on('scroll.' + name, base.toggleHeaders);
					base.$window.on('resize.' + name, base.toggleHeaders);
					base.$window.on('resize.' + name, base.updateWidth);
				};

				base.unbind = function() {
					// unbind window events by specifying handle so we don't remove too much
					base.$window.off('.' + name, base.toggleHeaders);
					base.$window.off('.' + name, base.updateWidth);
					base.$el.off('.' + name);
					base.$el.find('*').off('.' + name);
				};

				base.toggleHeaders = function() {
					base.$el
							.each(function() {
								var $this = $(this);

								var newTopOffset = isNaN(base.options.fixedOffset) ? base.options.fixedOffset
										.height()
										: base.options.fixedOffset;

								var offset = $this.offset();
								var scrollTop = base.$window.scrollTop()
										+ newTopOffset;
								var scrollLeft = base.$window.scrollLeft();

								if ((scrollTop > offset.top)
										&& (scrollTop < offset.top
												+ $this.height()
												- base.$clonedHeader.height())) {
									var newLeft = offset.left - scrollLeft;
									if (base.isSticky
											&& (newLeft === base.leftOffset)
											&& (newTopOffset === base.topOffset)) {
										return;
									}

									base.$originalHeader.css({
										'position' : 'fixed',
										'top' : newTopOffset,
										'margin-top' : 0,
										'left' : newLeft,
										'z-index' : 1
									// #18: opacity bug
									});
									base.$clonedHeader.css('display', '');
									base.isSticky = true;
									base.leftOffset = newLeft;
									base.topOffset = newTopOffset;

									// make sure the width is correct: the user might have resized the browser while in static mode
									base.updateWidth();
								} else if (base.isSticky) {
									base.$originalHeader.css('position',
											'static');
									base.$clonedHeader.css('display', 'none');
									base.isSticky = false;
								}
							});
				};

				base.updateWidth = function() {
					if (!base.isSticky) {
						return;
					}
					// Copy cell widths from clone
					var $origHeaders = $('th,td', base.$originalHeader);
					$('th,td', base.$clonedHeader).each(function(index) {

						var width, $this = $(this);

						if ($this.css('box-sizing') === 'border-box') {
							width = $this.outerWidth(); // #39: border-box bug
						} else {
							width = $this.width();
						}

						$origHeaders.eq(index).css({
							'min-width' : width,
							'max-width' : width
						});
					});

					// Copy row width from whole table
					base.$originalHeader.css('width', base.$clonedHeader
							.width());
				};

				base.updateOptions = function(options) {
					base.options = $.extend({}, defaults, options);
					base.updateWidth();
					base.toggleHeaders();
				};

				// Run initializer
				base.init();
			}

			// A plugin wrapper around the constructor,
			// preventing against multiple instantiations
			$.fn[name] = function(options) {
				return this.each(function() {
					var instance = $.data(this, 'plugin_' + name);
					if (instance) {
						if (typeof options === "string") {
							instance[options].apply(instance);
						} else {
							instance.updateOptions(options);
						}
					} else if (options !== 'destroy') {
						$.data(this, 'plugin_' + name,
								new Plugin(this, options));
					}
				});
			};

		})(jQuery, window);
	</script>



	<!-- Your GOOGLE ANALYTICS CODE Below -->
	<script type="text/javascript">
		var _gaq = _gaq || [];
		_gaq.push([ '_setAccount', 'UA-XXXXXXXX-X' ]);
		_gaq.push([ '_trackPageview' ]);

		(function() {
			var ga = document.createElement('script');
			ga.type = 'text/javascript';
			ga.async = true;
			ga.src = ('https:' == document.location.protocol ? 'https://ssl'
					: 'http://www')
					+ '.google-analytics.com/ga.js';
			var s = document.getElementsByTagName('script')[0];
			s.parentNode.insertBefore(ga, s);
		})();
	</script>



</body>

</html>
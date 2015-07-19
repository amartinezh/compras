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

</head>
<body oncontextmenu="return false">
	<!-- possible classes: minified, fixed-ribbon, fixed-header, fixed-width-->

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

								<form:form id="checkout-form" class="smart-form"
									novalidate="novalidate" method="POST" action="${accion}"
									ModelAttribute="user" commandName="user">
									<fieldset>
										<div class="row">
											<section class="col col-6">
												<label class="label">Usuario</label>
												<form:input path="id" />
											</section>
											<section class="col col-6">
												<label class="label">Contraseña</label>
												<form:input path="pass" autocomplete="off"/>
											</section>
											<section class="col col-6">
												<label class="label">Tipo Usuario</label>
												<form:select path="type.id" items="${ listype }" />
											</section>
											<section class="col col-6">
												<label class="label">Compañia</label>
												<form:select path="comp.id" items="${ listcomp }" />
											</section>
											<section class="col col-6">
												<label class="label">Moneda</label>
												<form:select path="curr.id" items="${listcur}" />
											</section>
											<section class="col col-6">
												<label class="label">Nivel</label>
												<form:select path="level.id" items="${listlev}" />
											</section>
											<section class="col col-6">
												<label class="label">Centro</label>
												<form:select path="cent.id" items="${ listcent }" />
											</section>
										</div>
									</fieldset>
									<footer>
										<button type="submit" class="btn btn-primary">
											<i class="fa fa-save"> Agregar Usuario </i>
										</button>
										<button type="button" onclick="location.href ='listar'"
											class="btn btn-default">
											<i class="fa fa-times"> Cancelar </i>
										</button>
									</footer>
								</form:form>

							</div>
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
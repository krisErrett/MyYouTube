
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<title>MyYouTube</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="">
		<meta name="author" content="">

		<!-- Le styles -->
		<link href="../css/bootstrap.css" rel="stylesheet">
		<style>
			body {
				padding-top: 60px; /* 60px to make the container go all the way to the bottom of the topbar */
			}
		</style>
		<style type="text/css">
			body, html { height: 100%; overflow: hidden; }
			.navbar-inner { height: 40px; }
			.scrollable { height: 100%; overflow: auto; }
			.max-height { height: 100%; }
			.no-overflow { overflow: hidden; }
			.pad100-top { padding-top: 80px; }
		</style>

		<script type="text/javascript" src="/jwplayer/jwplayer.js"></script>
		
		<link href="../css/bootstrap-responsive.css" rel="stylesheet">

		<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
		<!--[if lt IE 9]>
		  <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
		<![endif]-->
		
		<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
		<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
		<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>	
	</head>

	<body>
		<div class="navbar navbar-inverse navbar-fixed-top">
			<div class="navbar-inner">
				<div class="container">
					<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</a>
					<a class="brand" href="#">MyYouTube</a>
					<div class="nav-collapse collapse">
						<ul class="nav">
							<li class="dropdown">
								<a id="drop1" href="#" role="button" class="dropdown-toggle" data-toggle="dropdown">Upload <b class="caret"></b></a>
								<ul class="dropdown-menu" role="menu" aria-labelledby="drop1">
									<li><a tabindex="-1" href="http://google.com">Action</a></li>
									<li><a tabindex="-1" href="#anotherAction">Another action</a></li>
									<li class="divider"></li>
									<li><a tabindex="-1" href="#">Separated link</a></li>
								</ul>
							</li>
							<li><a href="#contact">List</a></li>
						</ul>
					</div><!--/.nav-collapse -->
				</div>
			</div>
		</div>

		<div class="container max-height no-overflow">
		
			<div class="row max-height pad100-top">
			
				<div class="span3 scrollable">
					<div>
						<!-- <a href="#" onclick="jwplayer().load({ 'file':'mp4:getting-started' }).play(); return false;">Click Here</a><br><br> -->
						<a href="url">Video here</a><br><br>
						<a href="url">Video here</a><br><br>
						<a href="url">Video here</a><br><br>
						<a href="url">Video here</a><br><br>
						
			
					</div>
				</div>

				<div class="span9 scrollable">
					<div>
						  <div id="container">Loading the video player ...</div>					
					</div>
				</div>
			</div><!--/row-->
			<hr>
		</div><!--/.container-->

    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="../js/jquery.js"></script>
	<script src="../js/bootstrap.js"></script>
	<script src="../js/bootstrap.min.js"></script>
	<script>
		$('.dropdown-toggle').dropdown()
	</script>
	<script src="../js/bootstrap-transition.js"></script>
	<script src="../js/bootstrap-transition.js"></script>
    <script src="../js/bootstrap-transition.js"></script>
    <script src="../js/bootstrap-alert.js"></script>
    <script src="../js/bootstrap-modal.js"></script>
    <script src="../js/bootstrap-dropdown.js"></script>
    <script src="../js/bootstrap-scrollspy.js"></script>
    <script src="../js/bootstrap-tab.js"></script>
    <script src="../js/bootstrap-tooltip.js"></script>
    <script src="../js/bootstrap-popover.js"></script>
    <script src="../js/bootstrap-button.js"></script>
    <script src="../js/bootstrap-collapse.js"></script>
    <script src="../js/bootstrap-carousel.js"></script>
    <script src="../js/bootstrap-typeahead.js"></script>

	<script type='text/javascript'>
		jwplayer("container").setup({
		        file: "mp4:getting-started",
				height: 480, width: 720,
				modes: video_modes,
		        modes: [
	  		             { type: "flash", src: "/jwplayer/jwplayer.flash.swf" },
		                 { type: "html5", config: { file: "http://doblfg9j68y74.cloudfront.net/getting-started.mp4", provider: "video" }}
  			           ],
		        provider: "rtmp",
		        streamer: "rtmp://s318o941ger6u2.cloudfront.net/cfx/st"
		    });
	</script>

  </body>
</html>

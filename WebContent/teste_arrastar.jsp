<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 5 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="pt" dir="ltr" itemscope itemtype="http://schema.org/Article">
<head>
<meta charset="utf-8">
<meta property="twitter:account_id" content="1593210261" />
<!-- Copyright (c) 2012 Google Inc.
   *
   * Licensed under the Apache License, Version 2.0 (the "License");
   * you may not use this file except in compliance with the License.
   * You may obtain a copy of the License at
   *
   *     http://www.apache.org/licenses/LICENSE-2.0
   *
   * Unless required by applicable law or agreed to in writing, software
   * distributed under the License is distributed on an "AS IS" BASIS,
   * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   * See the License for the specific language governing permissions and
   * limitations under the License.
   *
   * Author: Eric Bidelman - e.bidelman@google.com
   *
   * 
   *
  -->
<title>Função Arrastar e soltar nativa do HTML5 - HTML5 Rocks</title>
<meta name="description"
	content="A função arrastar-e-soltar agora também é um objeto de primeira classe em HTML5. Este artigo explica como aprimorar seus aplicativos Web adicionando a funcionalidade arrastar-e-soltar nativa.">
<meta name="keywords"
	content="html5,html 5,html5 demos,html5 examples,javascript,css3,notifications,geolocation,web workers,apppcache,file api,filereader,indexeddb,offline,audio,video,drag and drop,chrome,sse,mobile">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0">

<link rel="shortcut icon" href="/favicon.ico">
<link rel="alternate" type="application/rss+xml" title="RSS HTML5 Rocks"
	href="http://feeds.feedburner.com/html5rocks">

<meta itemprop="name"
	content="Função Arrastar e soltar nativa do HTML5 - HTML5 Rocks">
<meta itemprop="description"
	content="A função arrastar-e-soltar agora também é um objeto de primeira classe em HTML5. Este artigo explica como aprimorar seus aplicativos Web adicionando a funcionalidade arrastar-e-soltar nativa.">

<meta itemprop="image"
	content="http://www.html5rocks.com/static/images/html5rocks-logo-wings.png">



<meta name="twitter:card" content="summary">
<meta name="twitter:site" content="@ChromiumDev">
<meta name="twitter:creator" content="@ebidel">

<meta property="og:type" content="article">
<meta property="og:title"
	content="Função Arrastar e soltar nativa do HTML5 - HTML5 Rocks">
<meta property="og:url"
	content="http://www.html5rocks.com/pt/tutorials/dnd/basics/">
<meta property="og:description"
	content="A função arrastar-e-soltar agora também é um objeto de primeira classe em HTML5. Este artigo explica como aprimorar seus aplicativos Web adicionando a funcionalidade arrastar-e-soltar nativa.">
<meta property="og:image"
	content="http://www.html5rocks.com/static/images/profiles/ericbidelman.png">
<meta property="og:site_name"
	content="HTML5 Rocks - Um recurso para desenvolvedores HTML5 em web aberta">










<link
	href="//fonts.googleapis.com/css?family=Open+Sans:300,400,400italic,600,800|Source+Code+Pro"
	rel="stylesheet">

<link rel="apple-touch-icon"
	href="/static/images/identity/HTML5_Badge_64.png">
<link rel="apple-touch-icon-precomposed"
	href="/static/images/identity/HTML5_Badge_64.png">

<script src="js/modernizr.custom.82437.js"></script>




<style>
figure img {
	border: 1px solid #ccc;
}

h1,h2,h3,h4 {
	clear: both;
}
/* Prevent the contents of draggable elements from being selectable. */
[draggable] {
	-moz-user-select: none;
	-khtml-user-select: none;
	-webkit-user-select: none;
	user-select: none;
	/* Required to make elements draggable in old WebKit */
	-khtml-user-drag: element;
	-webkit-user-drag: element;
}

dd {
	padding: 5px 0;
}

.column {
  
  width: 150px;
  float: left;
  border: 2px solid #666666;
  background-color: #ccc;
  margin-right: 5px;
  -webkit-border-radius: 10px;
  -ms-border-radius: 10px;
  -moz-border-radius: 10px;
  border-radius: 10px;
  -webkit-box-shadow: inset 0 0 3px #000;
  -ms-box-shadow: inset 0 0 3px #000;
  box-shadow: inset 0 0 3px #000;
  text-align: center;
  cursor: move;
}

.column header {
	color: #fff;
	text-shadow: #000 0 1px;
	box-shadow: 5px;
	padding: 5px;
	background: -moz-linear-gradient(left center, rgb(0, 0, 0),
		rgb(79, 79, 79), rgb(21, 21, 21));
	background: -webkit-gradient(linear, left top, right top, color-stop(0, rgb(0, 0, 0)),
		color-stop(0.50, rgb(79, 79, 79)), color-stop(1, rgb(21, 21, 21)));
	background: -webkit-linear-gradient(left center, rgb(0, 0, 0),
		rgb(79, 79, 79), rgb(21, 21, 21));
	background: -ms-linear-gradient(left center, rgb(0, 0, 0),
		rgb(79, 79, 79), rgb(21, 21, 21));
	background: -o-linear-gradient(left center, rgb(0, 0, 0),
		rgb(79, 79, 79), rgb(21, 21, 21));
	border-bottom: 1px solid #ddd;
	-webkit-border-top-left-radius: 10px;
	-moz-border-radius-topleft: 10px;
	-ms-border-radius-topleft: 10px;
	-o-border-radius-topleft: 10px;
	border-top-left-radius: 10px;
	-webkit-border-top-right-radius: 10px;
	-moz-border-radius-topright: 10px;
	-ms-border-radius-topright: 10px;
	-o-border-radius-topright: 10px;
	border-top-right-radius: 10px;
}

#columns-full .column {
	-webkit-transition: -webkit-transform 0.2s ease-out;
	-moz-transition: -moz-transform 0.2s ease-out;
	-o-transition: -o-transform 0.2s ease-out;
	-ms-transition: -ms-transform 0.2s ease-out;
}

#columns-full .column.over,#columns-dragOver .column.over,#columns-dragEnd .column.over,#columns-almostFinal .column.over
	{
	border: 2px dashed #000;
}

#columns-full .column.moving {
	opacity: 0.25;
	-webkit-transform: scale(0.8);
	-moz-transform: scale(0.8);
	-ms-transform: scale(0.8);
	-o-transform: scale(0.8);
}

#columns-full .column .count {
	padding-top: 15px;
	font-weight: bold;
	text-shadow: #fff 0 1px;
}
</style>

</head>
<body>






<div id="columns-full">
    <div id="5" class="column"><header>Direcao Hidraulica</header><p>Texto1</p><div class="count" data-col-moves="0"></div></div>
    <div id="7" class="column"><header>Ar Condicionado</header><p>Texto2</p><div class="count" data-col-moves="0"></div></div>
    <div id="" class="column"><header>Teto Solar</header><div class="count" data-col-moves="0"></div></div>
    <div class="column"><header>Roda de liga leve</header><div class="count" data-col-moves="0"></div></div>
  </div>
   
	<br>
	<br>
	<p>----------------------------------------------------------------------------</p>

<div id="20"  class ="column" draggable="true">
<p>Teste</p>
</div>






	

  
	

	<!-- FIM DA PRINCIPAL -->

	<script>
		// Using this polyfill for safety.
		Element.prototype.hasClassName = function(name) {
			return new RegExp("(?:^|\\s+)" + name + "(?:\\s+|$)")
					.test(this.className);
		};

		Element.prototype.addClassName = function(name) {
			if (!this.hasClassName(name)) {
				this.className = this.className ? [ this.className, name ]
						.join(' ') : name;
			}
		};

		Element.prototype.removeClassName = function(name) {
			if (this.hasClassName(name)) {
				var c = this.className;
				this.className = c.replace(new RegExp("(?:^|\\s+)" + name
						+ "(?:\\s+|$)", "g"), "");
			}
		};

		var samples = samples || {};

		(function() {
			var id_ = 'columns-full';
			var cols_ = document.querySelectorAll('#' + id_ + ' .column');
			var dragSrcEl_ = null;
   
			
			this.handleDragStart = function(e) {
				e.dataTransfer.effectAllowed = 'move';
				e.dataTransfer.setData('text/html', this.innerHTML);
				

				dragSrcEl_ = this;

				// this/e.target is the source node.
				this.addClassName('moving');
			};

			this.handleDragOver = function(e) {
				if (e.preventDefault) {
					e.preventDefault(); // Allows us to drop.
				}

				e.dataTransfer.dropEffect = 'move';

				return false;
			};

			this.handleDragEnter = function(e) {
				this.addClassName('over');
			};

			this.handleDragLeave = function(e) {
				// this/e.target is previous target element.
				this.removeClassName('over');
			};

			this.handleDrop = function(e) {
				// this/e.target is current target element.

		
					dragSrcEl_.innerHTML = this.innerHTML;
					this.innerHTML += e.dataTransfer.getData('text/html');
					window.alert(e.dataTransfer.getData('text/html'));

					// Set number of times the column has been moved.
					var count = this.querySelector('.count');
					var newCount = parseInt(count
							.getAttribute('data-col-moves')) + 1;
					count.setAttribute('data-col-moves', newCount);
					count.textContent = '';
				

				return false;
				
			};

			this.handleDragEnd = function(e) {
				// this/e.target is the source node.
				[].forEach.call(cols_, function(col) {
					col.removeClassName('over');
					col.removeClassName('moving');
				});
			};

			[].forEach.call(cols_, function(col) {
				col.setAttribute('draggable', 'true'); // Enable columns to be draggable.
				col.addEventListener('dragstart', this.handleDragStart, false);
				col.addEventListener('dragenter', this.handleDragEnter, false);
				col.addEventListener('dragover', this.handleDragOver, false);
				col.addEventListener('dragleave', this.handleDragLeave, false);
				col.addEventListener('drop', this.handleDrop, false);
				col.addEventListener('dragend', this.handleDragEnd, false);
			});
		})();

		(function() {
			var id_ = 'outro';
			var cols_ = document.querySelectorAll('#' + id_ + ' .column');
			var dragSrcEl_ = null;

			this.handleDragStart = function(e) {
				e.dataTransfer.effectAllowed = 'move';
				e.dataTransfer.setData('text/html', this.innerHTML);

				dragSrcEl_ = this;

				// this/e.target is the source node.
				this.addClassName('moving');
			};

			this.handleDragOver = function(e) {
				if (e.preventDefault) {
					e.preventDefault(); // Allows us to drop.
				}

				e.dataTransfer.dropEffect = 'move';

				return false;
			};

			this.handleDragEnter = function(e) {
				this.addClassName('over');
			};

			this.handleDragLeave = function(e) {
				// this/e.target is previous target element.
				this.removeClassName('over');
			};

			this.handleDrop = function(e) {
				// this/e.target is current target element.

				

					dragSrcEl_.innerHTML = this.innerHTML;
					this.innerHTML += e.dataTransfer.getData('text/html');

					// Set number of times the column has been moved.
					var count = this.querySelector('.count');
					var newCount = parseInt(count
							.getAttribute('data-col-moves')) + 1;
					count.setAttribute('data-col-moves', newCount);
					count.textContent = '';
				

				return false;
			};

			this.handleDragEnd = function(e) {
				// this/e.target is the source node.
				[].forEach.call(cols_, function(col) {
					col.removeClassName('over');
					col.removeClassName('moving');
				});
			};

			[].forEach.call(cols_, function(col) {
				col.setAttribute('draggable', 'true'); // Enable columns to be draggable.
				col.addEventListener('dragstart', this.handleDragStart, false);
				col.addEventListener('dragenter', this.handleDragEnter, false);
				col.addEventListener('dragover', this.handleDragOver, false);
				col.addEventListener('dragleave', handleDragLeave, false);
				col.addEventListener('drop', this.handleDrop, false);
				col.addEventListener('dragend',this.handleDragEnd, false);
			});
		})();
	</script>



	




</body>
</html>
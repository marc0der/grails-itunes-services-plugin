<html>
    <head>
        <title>Test Page for iTunes Service Plugin</title>
		<meta name="layout" content="main" />
    </head>
    <body>
		<div id="pageBody">
		    <g:each var="album" in="${newreleases}">
		        <p>Album: <a href="${album.albumLink}">${album.album}</a> by <a href="${album.artistLink}">${album.artist}</a></p>
		    </g:each>
		</div>
    </body>
</html>

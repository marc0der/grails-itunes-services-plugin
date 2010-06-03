<html>
    <head>
        <title>Test Page for iTunes Service Plugin</title>
		<meta name="layout" content="main" />
    </head>
    <body>
		<div>
		    <h1>New Releases</h1>
		    <g:each var="album" in="${newreleases}">
		        <p>Album: <a href="${album.link}">${album.name}</a> by <a href="${album.artistLink}">${album.artist}</a></p>
		    </g:each>
		</div>
    </body>
</html>

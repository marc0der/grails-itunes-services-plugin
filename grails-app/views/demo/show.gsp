<html>
    <head>
        <title>Demo Page for iTunes Service Plugin</title>
		<meta name="layout" content="main" />
    </head>
    <body>
        <h1>Grails iTunes Services Plugin Demo Page</h1>
		<div>
		    <h2>New Releases</h2>
		    <g:each var="album" in="${results.newReleases}">
		        <p><strong>${album.rank}.</strong> Album: <a href="${album.link}">${album.name}</a> by <a href="${album.artistLink}">${album.artist}</a></p>
		        <p><img src="${album.image}"/> Price: ${album.price}   Release: <g:formatDate format="d MMM, yyyy" date="${album.releaseDate}" /></p>
		    </g:each>
		</div>
		<div>
		    <h2>Just Added Albums</h2>
		    <g:each var="album" in="${results.justAddedAlbums}">
                <p><strong>${album.rank}.</strong> Album: <a href="${album.link}">${album.name}</a> by <a href="${album.artistLink}">${album.artist}</a></p>
                <p><img src="${album.image}"/> Price: ${album.price}   Release: <g:formatDate format="d MMM, yyyy" date="${album.releaseDate}" /></p>
		    </g:each>
		</div>
        <div>
            <h2>Featured Albums</h2>
            <g:each var="album" in="${results.featuredAlbums}">
                <p><strong>${album.rank}.</strong> Album: <a href="${album.link}">${album.name}</a> by <a href="${album.artistLink}">${album.artist}</a></p>
                <p><img src="${album.image}"/> Price: ${album.price}   Release: <g:formatDate format="d MMM, yyyy" date="${album.releaseDate}" /></p>
            </g:each>
        </div>
        <div>
            <h2>Top Albums</h2>
            <g:each var="album" in="${results.topAlbums}">
                <p><strong>${album.rank}.</strong> Album: <a href="${album.link}">${album.name}</a> by ${album.artist}</p>
                <p><img src="${album.image}"/> Price: ${album.price}   Release: <g:formatDate format="d MMM, yyyy" date="${album.releaseDate}" /></p>
            </g:each>
        </div>
    </body>
</html>

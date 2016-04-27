<?xml version="1.0" encoding="UTF-8"?>
<kml xmlns:gx="http://www.google.com/kml/ext/2.2" xmlns:kml="http://www.opengis.net/kml/2.2" xmlns:atom="http://www.w3.org/2005/Atom">
    <Document>
        <name>KmlFile</name>
        <Style></Style>
        <Folder>
			<name>stock</name>
			<open>1</open>
			<#list placemarks as pm>
				<Placemark>
					<name>${pm.name}</name>
					<open>${pm.open}</open>
					<description>
						<![CDATA[${pm.description}]]></description>
					<styleUrl>#1052804</styleUrl>
					<Point>
						<coordinates>${pm.longitude},${pm.latitude},0</coordinates>
					</Point>
				</Placemark>
			</#list>
		</Folder>
    </Document>
</kml>
import eu.mihosoft.vrl.v3d.CSG
import eu.mihosoft.vrl.v3d.Cube
import eu.mihosoft.vrl.v3d.Parabola

def frameWidth =4*25.4
def frameHeight =6*25.4

def glassThickness = 5.2
def baseHeight = 10
def glassOver=2
def supportWidth =20

CSG glass = new Cube(frameWidth+glassOver*2,glassThickness,frameHeight).toCSG()
		.toXMin()
		.toZMin()
		.movez(baseHeight)
		.movex(-glassOver*1)
CSG glasskw = new Cube(frameWidth+glassOver*3,glassThickness,frameHeight).toCSG()
		.toXMin()
		.toZMin()
		.movez(baseHeight)
		.movex(-glassOver*1.5)
CSG supportSide = 		Parabola.coneByHeight(supportWidth, frameHeight/4+baseHeight)
		.rotx(90)
		.toZMin()
CSG box=supportSide.getBoundingBox()
CSG supportleft = supportSide
		.difference(	box.toXMin())
		.difference(glasskw)
CSG supportright = supportSide
		.difference(	box.toXMax())
		.movex(frameWidth)
		.difference(glasskw)

CSG base = Parabola.coneByHeight(supportWidth+5, baseHeight)
		.rotx(90)
		.toZMin()

CSG supportConnectCone = Parabola.coneByHeight(glassThickness, baseHeight)
		.rotx(90)
		.toZMin()
CSG supportThickness = CSG.unionAll([
	supportConnectCone.movey(glassThickness/2),
	supportConnectCone.movey(glassThickness/-2),
	supportConnectCone.movey(glassThickness/2).movex(frameWidth - 2),
	supportConnectCone.movey(glassThickness/-2).movex(frameWidth - 2)
])
.hull()
glass.setManufacturing({ toMfg ->
	return toMfg
			.rotx(-90)// fix the orentation
			.toZMin()//move it down to the flat surface
})
glass.setColor(javafx.scene.paint.Color.WHITE);
glass.addExportFormat("svg")
return [
	CSG.unionAll([
		base,
		base.movex(frameWidth - 2),
		supportThickness,
		supportleft,
		supportright
	]),
	glass]

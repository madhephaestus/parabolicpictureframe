//Your code here
def frameWidth =4*25.4
def frameHeight =6*24.5

def glassThickness = 6.5
def baseHeight = 10
def glassOver=1
def supportWidth =20 

def glass = new Cube(frameWidth+glassOver+glassOver,glassThickness,frameHeight).toCSG()
	.toXMin()
	.toZMin()
	.movez(baseHeight)
	.movex(-glassOver)

def supportSide = 		Parabola.coneByHeight(supportWidth, frameHeight/2+baseHeight)
				.rotx(90)
				.toZMin()
def box=supportSide.getBoundingBox()
def supportleft = supportSide
					.difference(	box.toXMin())	
					.difference(glass)
def supportright = supportSide
					.difference(	box.toXMax())
					.movex(frameWidth)
					.difference(glass)

def base = Parabola.coneByHeight(supportWidth+5, baseHeight)
			.rotx(90)
			.toZMin()

def supportConnectCone = Parabola.coneByHeight(glassThickness, baseHeight)
			.rotx(90)
			.toZMin()
def supportThickness = CSG.unionAll([
					supportConnectCone.movey(glassThickness/2),
				     supportConnectCone.movey(glassThickness/-2),
					supportConnectCone.movey(glassThickness/2).movex(frameWidth),
				     supportConnectCone.movey(glassThickness/-2).movex(frameWidth)])	
				     .hull()
glass.setColor(javafx.scene.paint.Color.WHITE);
glass.addExportFormat("svg")
return [CSG.unionAll([
base,
base.movex(frameWidth),
supportThickness,
supportleft,
supportright
]),glass]			   
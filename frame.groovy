//Your code here
def frameWidth =4*25.4
def frameHeight =6*24.5

def glassThickness = 6.5
def baseHeight = 10
def glassOver=1

def glass = new Cube(frameWidth+glassOver+glassOver,glassThickness,frameHeight).toCSG()
	.toXMin()
	.toZMin()
	.movez(baseHeight)
	.movex(-glassOver)

def base = Parabola.coneByHeight(20, baseHeight)
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
return [CSG.unionAll([
base,
base.movex(frameWidth),
supportThickness
]),glass]			   
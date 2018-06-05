package blacklinden.kanvasz;

/**
 * Created by Vitya on 2018. 01. 23..
 */

import min3d.core.Object3dContainer;
import min3d.core.RendererActivity;
import min3d.parser.IParser;
import min3d.parser.Parser;
import min3d.vos.Color4;
import min3d.vos.Light;
import min3d.vos.ShadeModel;

public class min3d3d extends RendererActivity {

    private Object3dContainer faceObject3D;

    @Override
    public void initScene() {
        scene.lights().add(new Light());
        scene.lights().add(new Light());

        Light myLight = new Light();
        myLight.position.setZ(150);
       myLight.ambient.setAll(158,212,144,1);
        scene.lights().add(myLight);

        IParser myParser = Parser.createParser(Parser.Type.OBJ, getResources(), "blacklinden.kanvasz:raw/bobita_obj", true);
        myParser.parse();

        faceObject3D = myParser.getParsedObject();
        faceObject3D.position().x = faceObject3D.position().y = faceObject3D.position().z = 0;
        faceObject3D.scale().x = faceObject3D.scale().y = faceObject3D.scale().z = 0.5f;
// mértezni kell ha kell
        faceObject3D.scale().x = faceObject3D.scale().y = faceObject3D.scale().z = 0.5f;

        scene.addChild(faceObject3D);

    }

    @Override
    public void updateScene() {

        //faceObject3D.rotation().y += 0.8;
        //faceObject3D.rotation().z += 1;
        faceObject3D.shadeModel(ShadeModel.SMOOTH);
        }
}

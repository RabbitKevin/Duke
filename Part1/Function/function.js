function swapRedGreen(pixel){
    var red = pixel.getRed();
    var green = pixel.getGreen();
    pixel.setRed(green);
    pixel.setGreen(red);
}

function crop(image, width, height){
    var newImage = new SimpleImage(width, height);
    for(var pixel of image.values()){
        var x = pixel.getX();
        var y = pixel.getY();
        if(x < width && y <height){
            newImage.getPixel(x,y).setAllFrom(pixel);
        }
    }
    return newImage;
}

function pixchange(pixval){
    var x = Math.floor(pixval/16) * 16;
    return x;
}
function chop2hide(image){
    for(var px of image.values()){
        px.setRed(pixchange(px.getRed()));
        px.setGreen(pixchange(px.getGreen()));
        px.setBlue(pixchange(px.getBlue()));
    }
    return image;
}
function shift(im){
  var nim = new SimpleImage(im.getWidth(),
                            im.getHeight());
  for(var px of im.values()){
    var x = px.getX();
    var y = px.getY();
    var npx = nim.getPixel(x,y);
    npx.setRed(Math.floor(px.getRed()/16));
    npx.setGreen(Math.floor(px.getGreen()/16));
    npx.setBlue(Math.floor(px.getBlue()/16));
  }
  return nim;
}

function newpv(p, q){
    if(p+q >255) return 255;
    return p+q;
}

function combine(image1, image2){
    var width = image2.getWidth();
    var height = image2.getHeight();
    image1 = crop(image1, width, height);
    image2 = shift(image2);
    var newImage = new SimpleImage(width, height);
    for(var pixel of newImage.values()){
        var x = pixel.getX();
        var y = pixel.getY();
        var one = image1.getPixel(x,y);
        var two = image2.getPixel(x,y);
        pixel.setRed(newpv(one.getRed(), two.getRed()));
        pixel.setGreen(newpv(one.getGreen(), two.getGreen()));
        pixel.setBlue(newpv(one.getBlue(), two.getBlue()));
    }
    return newImage;
}

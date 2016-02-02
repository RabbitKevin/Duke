function negativeEffect(image){
    for(var px of image.values()){
        var average = (px.getRed()+px.getBlue()+px.getGreen())/3;
        if(average >= 100){
            px.setRed(255);
            px.setBlue(255);
            px.setGreen(255);
        }
        else{
            px.setRed(0);
            px.setBlue(0);
            px.setGreen(0);
        }
    }
}
//Two images with same size, hide one in another with changble digits
function extract(image){
    var extracted = new SimpleImage(image.getWidth(), image.getHeight());
    for(var px of image.values()){
        var piexl = extracted.getPixel(px.getX(), px.getY());
        pixel.setRed((px.getRed()&3)*64);
        pixel.setBlue((px.getRed()&3)*64);
        pixel.setBlue((px.getRed()&3)*64);
    }
    return extracted;
}
//Hide image2 in imgae1, same size
function hide(image1, image2){
    for(var px of image1.values()){
        var pixel = image2.getPixel(px.getX(), px.getY());
        px.setRed((px.getRed()&252) | ((pixel.getRed()&192)/64);
        px.setBlue((px.getBlue()&252) | ((pixel.getBlue()&192)/64);
        px.setGreen((px.getGreen()&252) | ((pixel.getGreen()&192)/64);
    }
    return image1;
}

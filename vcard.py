from flask import Flask, jsonify, request

from DBConnection import Db

app = Flask(__name__)


@app.route('/login',methods=['post'])
def login():
    uname=request.form['username']
    password=request.form['password']
    db=Db()
    qry="SELECT * FROM `login` WHERE `username`='"+uname+"' AND `password`='"+password+"'"
    res=db.selectOne(qry)
    print("---------------",res)
    if res is not None:

        return jsonify(status='ok',lid=res['login_id'],type=res["type"])
    else:
        return jsonify(status='no')

    #------------------------------------------Admin_________________________________________


@app.route('/admin_view_manuf_rationgs',methods=["post"])
def admin_view_manuf_rationgs():
    q="SELECT `feedback`.*,`manufacturer`.* FROM `feedback` INNER JOIN `manufacturer` ON `feedback`.`tolid`=`manufacturer`.`manufacturer_login_id`"
    d=Db()
    res=d.select(q)
    return jsonify(status="ok",users=res)


@app.route('/admin_view_manufacturer_serach',methods=['post'])
def admin_view_manufacturer_serach():
    name=request.form['name']
    db = Db()
    qry="SELECT `manufacturer`.* FROM `manufacturer` INNER JOIN `login` ON `manufacturer`.`manufacturer_login_id`=`login`.`login_id` WHERE `login`.`type`='manufacturer' and(manufacturer.name like '%"+name+"%' or `manufacturer`.`company_name`='%"+name+"%') "
    res = db.select(qry)
    print(qry)
    return jsonify(status='ok',users=res)

@app.route('/admin_block_manufact',methods=["post"])
def admin_block_manufact():
    lid=request.form["lid"]
    q="UPDATE login SET `type`='blocked' WHERE `login_id`='"+lid+"'"
    d=Db()
    res=d.update(q)
    return jsonify(status="ok")
@app.route('/admin_view_owners_search',methods=["post"])
def admin_view_owners_search():
    name=request.form["name"]
    q="SELECT * FROM `entrepreneur` where shop_name like '%"+name+"%'"
    d=Db()
    res=d.select(q)
    return jsonify(status="ok",users=res)

@app.route('/admin_view_entrt_rating',methods=["post"])
def admin_view_entrt_rating():
    q = "SELECT `feedback`.*,`entrepreneur`.* FROM `feedback` INNER JOIN `entrepreneur` ON `feedback`.`tolid`=`entrepreneur`.`ent_login_id`"
    d = Db()
    res = d.select(q)
    return jsonify(status="ok", users=res)

#---------------manuf------------------

@app.route('/manuf_view_order_from_users',methods=["post"])
def manuf_view_order_from_users():
    lid=request.form["lid"]
    q="SELECT `entrepreneur`.*,`ordermain`.* FROM `ordermain` INNER JOIN `entrepreneur` ON `ordermain`.`uid`=`entrepreneur`.`ent_login_id` WHERE `ordermain`.`toid`='"+lid+"' ORDER BY `ordermain`.`ordermain_id` DESC"
    d=Db()
    res=d.select(q)
    return jsonify(status="ok",data=res)

@app.route('/change_password', methods=['post'])
def change_password():
    current_password=request.form['current_password']
    new_password=request.form['new_password']
    confirm_password=request.form['confirm_password']
    lid=request.form['lid']
    db=Db()
    qry = "SELECT * FROM `login` WHERE `password`='" + current_password + "'"
    res = db.selectOne(qry)
    if res!=None:
        if new_password==confirm_password:
            qry="UPDATE login SET password='"+new_password+"' where login_id='"+lid+"'"
            res=db.update(qry)
            return jsonify(status='ok')
        else:
            return jsonify(status="no")
    else:
        return jsonify(status="no")

@app.route('/ent_change_password', methods=['post'])
def ent_change_password():
    current_password = request.form['current_password']
    new_password = request.form['new_password']
    confirm_password = request.form['confirm_password']
    db = Db()
    qry = "SELECT * FROM `login` WHERE `password`='" + current_password + "'"
    res = db.selectOne(qry)
    if res != None:
        if new_password == confirm_password:
            qry = "UPDATE login SET password='" + new_password + "'"
            res = db.update(qry)
            return jsonify(status='ok')
        else:
            return jsonify(status='no')
    else:
        return jsonify(status='no')


@app.route('/man_change_password', methods=['post'])
def man_change_password():
        current_password = request.form['current_password']
        new_password = request.form['new_password']
        confirm_password = request.form['confirm_password']
        lid=request.form["lid"]
        db = Db()
        qry = "SELECT * FROM `login` WHERE `password`='" + current_password + "'"
        res = db.selectOne(qry)
        print(res)
        if res is not None:
            if new_password == confirm_password:
                qry = "UPDATE login SET password='" + new_password + "' where login_id='"+lid+"'"
                res = db.update(qry)
                return jsonify(status='ok')

@app.route('/customer_signup',methods=['post'])
def customer_signup():
    name=request.form['name']
    place=request.form['place']
    post=request.form['post']
    district=request.form['district']
    pin=request.form['pin']
    phone_number=request.form['phone_number']
    email=request.form['email']
    password=request.form['password']
    db=Db()
    qry="INSERT INTO `login`(username, password, type) VALUES('"+email+"','"+password+"','Customer')"
    lid=str(db.insert(qry))
    qry="INSERT INTO `customer`(cust_login_id, username, place, post, district, pin, phone_number, email, password) VALUES('"+lid+"','"+name+"','"+place+"','"+post+"','"+district+"','"+pin+"','"+phone_number+"','"+email+"','"+password+"')"
    res=db.insert(qry)
    return jsonify(status='ok')


@app.route('/entrepreneur_signup',methods=['post'])
def entrepreneur_signup():
    name=request.form['name']
    shop_name=request.form['shop_name']
    place=request.form['place']
    post=request.form['post']
    district=request.form['district']
    pin=request.form['pin']
    image=request.form['image']
    import time, datetime
    from encodings.base64_codec import base64_decode
    import base64

    timestr = time.strftime("%Y%m%d-%H%M%S")
    print(timestr)
    a = base64.b64decode(image)
    fh = open("C:\\Users\\user\\PycharmProjects\\vcard\static\\entr\\" + timestr + ".jpg", "wb")
    path = "/static/entr/" + timestr + ".jpg"
    fh.write(a)
    fh.close()
    location=request.form['location']
    latitude=request.form['latitude']
    longitude=request.form['longitude']
    phone_number=request.form['phone_number']
    email=request.form['email']
    password=request.form['password']
    upi=request.form['upi']
    db=Db()
    qry="INSERT INTO `login`(username, password, type) VALUES('"+email+"','"+password+"','Entrepreneur')"
    lid=str(db.insert(qry))
    qry="INSERT INTO `entrepreneur`(ent_login_id,username, shop_name, place, post, district, pin, image, location, latitude, longitude, phone_number, email, upi) VALUES('"+lid+"','"+name+"','"+shop_name+"','"+place+"','"+post+"','"+district+"','"+pin+"','"+path+"','"+location+"','"+latitude+"','"+longitude+"','"+phone_number+"','"+email+"','"+upi+"')"
    res=db.insert(qry)
    return jsonify(status='ok')


@app.route('/manufacturer_signup',methods=['post'])
def manufacturer_signup():
    name=request.form['name']
    company_name=request.form['company_name']
    place=request.form['place']
    post=request.form['post']
    district=request.form['district']
    pin=request.form['pin']
    phone_number=request.form['phone_number']
    email=request.form['email']
    password=request.form['password']
    db=Db()
    qry="INSERT INTO `login`(username, password, type) VALUES('"+email+"','"+password+"','manufacturer')"
    lid=str(db.insert(qry))
    qry="INSERT INTO `manufacturer`(manufacturer_login_id, username, company_name, place, post, district, pin, phone_number, email, password) VALUES('"+lid+"','"+name+"','"+company_name+"','"+place+"','"+post+"','"+district+"','"+pin+"','"+phone_number+"','"+email+"','"+password+"')"
    res=db.insert(qry)
    return jsonify(status='ok')



@app.route('/product',methods=['post'])
def product():
    product_name=request.form['product_name']
    price=request.form['price']
    quantity=request.form['quantity']
    # type=request.form['type']
    made_id=request.form['made_id']
    brand_name=request.form['brand_name']
    image=request.form['image']
    import time, datetime
    from encodings.base64_codec import base64_decode
    import base64

    timestr = time.strftime("%Y%m%d-%H%M%S")
    print(timestr)
    a = base64.b64decode(image)
    fh = open("C:\\Users\\prana\\Desktop\\BINDHIYA\\vcard\\static\\product\\" + timestr + ".jpg", "wb")
    path = "/static/product/" + timestr + ".jpg"
    fh.write(a)
    fh.close()
    db=Db()
    qry="INSERT INTO `product`(product_name, price, quantity, type, made_id,brand_name, image) VALUES('"+product_name+"','"+price+"','"+quantity+"','Entrepreneur ','"+made_id+"','"+brand_name+"','"+path+"')"
    res=db.insert(qry)
    return jsonify(status='ok')


@app.route('/man_product',methods=['post'])
def man_product():
    product_name=request.form['product_name']
    price=request.form['price']
    quantity=request.form['quantity']
    type=request.form['type']
    made_id=request.form['made_id']
    brand_name = request.form['brand_name']
    image=request.form['image']
    import time, datetime
    from encodings.base64_codec import base64_decode
    import base64

    timestr = time.strftime("%Y%m%d-%H%M%S")
    print(timestr)
    a = base64.b64decode(image)
    fh = open("C:\\Users\\prana\\Desktop\\BINDHIYA\\vcard\\static\\product\\" + timestr + ".jpg", "wb")
    path = "/static/product/" + timestr + ".jpg"
    fh.write(a)
    fh.close()
    db=Db()
    qry="INSERT INTO `product`(product_name, price, quantity, type, made_id,brand_name, image) VALUES('"+product_name+"','"+price+"','"+quantity+"','"+type+"','"+made_id+"','"+brand_name+"','"+path+"')"
    res=db.insert(qry)
    return jsonify(status='ok')

@app.route('/view_product',methods=['post'])
def view_product():
    lid=request.form['lid']
    db = Db()
    qry="SELECT * FROM product WHERE made_id='" + lid + "'"
    res = db.select(qry)
    return jsonify(status='ok',users=res)

@app.route('/view_more',methods=['post'])
def view_more():
    product_id=request.form['product_id']
    db = Db()
    qry="SELECT * FROM product WHERE product_id='" + product_id + "'"
    res = db.selectOne(qry)
    return jsonify(status='ok',pid=res["product_id"])


@app.route('/view_man_product',methods=['post'])
def view_man_product():
    lid=request.form['lid']
    db = Db()
    qry="SELECT * FROM product WHERE TYPE='Manufacturer'AND`made_id`='"+lid+"'"
    res = db.select(qry)
    print(qry)
    return jsonify(status='ok',users=res)

@app.route('/view_manufacturer',methods=['post'])
def view_manufacturer():
    # lid=request.form['lid']
    db = Db()
    # qry="SELECT * FROM manufacturer "
    qry="SELECT `manufacturer`.* FROM `manufacturer` INNER JOIN `login` ON `manufacturer`.`manufacturer_login_id`=`login`.`login_id` WHERE `login`.`type`='manufacturer' "
    res = db.select(qry)
    print(qry)
    return jsonify(status='ok',users=res)


@app.route('/view_man_more',methods=['post'])
def view_man_more():
    manufacturer_login_id=request.form['manufacturer_login_id']
    db = Db()
    qry="SELECT * FROM product WHERE made_id='" + manufacturer_login_id + "'"
    data = db.select(qry)
    return jsonify(status='ok',users=data)

@app.route('/ent_add_to_cart',methods=["post"])
def ent_add_to_cart():
    u_lid=request.form["ulid"]
    product_id=request.form["pid"]
    quantity=request.form["qty"]
    owner_lid=request.form["owlid"]
    q="INSERT INTO `cart`(`u_lid`,`product_id`,`quantity`,`owner_lid`)VALUES('"+u_lid+"','"+product_id+"','"+quantity+"','"+owner_lid+"')"
    d=Db()
    res=d.insert(q)
    return jsonify(status="ok")


@app.route('/ent_view_cart_manuf',methods=["post"])
def ent_view_cart_manuf():
    ulid=request.form["lid"]
    q="SELECT COUNT(`cart`.`owner_lid`)as pcount,`manufacturer`.`company_name`,`manufacturer`.`phone_number`,`manufacturer`.`manufacturer_login_id` FROM `cart` INNER JOIN `manufacturer` ON `cart`.`owner_lid`=`manufacturer`.`manufacturer_login_id` WHERE `cart`.`u_lid`='"+ulid+"'"
    d=Db()
    res=d.select(q)
    return jsonify(status="ok",users=res)
@app.route('/ent_view_cart_product',methods=["post"])
def ent_view_cart_product():
    ulid=request.form["lid"]
    shoplid=request.form["shoplid"]
    q="SELECT `cart`.`cart_id`,`cart`.`u_lid`,`cart`.`product_id`,`cart`.`quantity` as cqty,`cart`.`owner_lid`,`product`.* FROM `cart` INNER JOIN `product` ON `cart`.`product_id`=`product`.`product_id` WHERE `cart`.`u_lid`='"+ulid+"' AND cart.`owner_lid`='"+shoplid+"'"
    d=Db()
    res=d.select(q)
    return jsonify(status="ok",data=res)

@app.route('/ent_view_previous_orders',methods=["post"])
def ent_view_previous_orders():
    lid=request.form["lid"]
    q="SELECT `ordermain`.*,`manufacturer`.* FROM `ordermain` INNER JOIN `manufacturer` ON `ordermain`.`toid`=`manufacturer`.`manufacturer_login_id` WHERE `ordermain`.`uid`='"+lid+"'"
    d=Db()
    r=d.select(q)
    return jsonify(status="ok",data=r)
@app.route('/ent_view_order_from_users',methods=["post"])
def ent_view_order_from_users():
    lid=request.form["lid"]
    q="SELECT `customer`.*,`ordermain`.* FROM `ordermain` INNER JOIN `customer` ON `ordermain`.`uid`=`customer`.`cust_login_id` WHERE `ordermain`.`toid`='"+lid+"' ORDER BY `ordermain`.`ordermain_id` DESC"
    d=Db()
    print(q)
    res=d.select(q)
    return jsonify(status="ok",data=res)



#_____________________user_____________________________
@app.route('/user_view_owners',methods=["post"])
def user_view_owners():
    q="SELECT entrepreneur.* FROM `entrepreneur` INNER JOIN `login` ON `entrepreneur`.`ent_login_id`=`login`.`login_id` WHERE `login`.`type`='Entrepreneur'"
    d=Db()
    res=d.select(q)
    return jsonify(status="ok",users=res)
@app.route('/user_view_owner_product',methods=["post"])
def user_view_owner_product():
    lid=request.form["ownerlid"]
    q="SELECT * FROM `product` WHERE `made_id`='"+lid+"'"
    d=Db()
    res=d.select(q)
    return jsonify(status="ok",users=res)
@app.route('/user_add_to_cart',methods=["post"])
def user_add_to_cart():
    u_lid=request.form["ulid"]
    product_id=request.form["pid"]
    quantity=request.form["qty"]
    owner_lid=request.form["owlid"]
    q="INSERT INTO `cart`(`u_lid`,`product_id`,`quantity`,`owner_lid`)VALUES('"+u_lid+"','"+product_id+"','"+quantity+"','"+owner_lid+"')"
    d=Db()
    res=d.insert(q)
    return jsonify(status="ok")

@app.route('/user_view_cart_owner',methods=["post"])
def user_view_cart_owner():
    ulid=request.form["lid"]
    q="SELECT COUNT(`cart`.`owner_lid`) as pcount,`entrepreneur`.`shop_name` ,`entrepreneur`.`phone_number`,`entrepreneur`.`ent_login_id` FROM `cart` INNER JOIN entrepreneur ON `cart`.`owner_lid`=`entrepreneur`.`ent_login_id` WHERE `cart`.`u_lid`='"+ulid+"'"
    d=Db()
    res=d.select(q)
    return jsonify(status="ok",users=res)

@app.route('/user_view_cart_product',methods=["post"])
def user_view_cart_product():
    ulid=request.form["lid"]
    shoplid=request.form["shoplid"]
    q="SELECT `cart`.`cart_id`,`cart`.`u_lid`,`cart`.`product_id`,`cart`.`quantity` as cqty,`cart`.`owner_lid`,`product`.* FROM `cart` INNER JOIN `product` ON `cart`.`product_id`=`product`.`product_id` WHERE `cart`.`u_lid`='"+ulid+"' AND cart.`owner_lid`='"+shoplid+"'"
    d=Db()
    res=d.select(q)
    return jsonify(status="ok",data=res)
@app.route("/delete_cart", methods=['post'])
def delete_cart():
    cart_id=request.form['cart_id']
    print(cart_id)
    qry = "DELETE FROM `cart` WHERE `cart_id`='"+cart_id+"'"
    print(qry)
    db=Db()
    res=db.delete(qry)
    print(res)
    return jsonify(status="ok")


@app.route("/user_buy_product", methods=['post'])
def user_buy_product():
    db=Db()
    print("hi")
    bank_name = request.form['bank_name']
    print("hoi")
    accno = request.form['account_no']
    password = request.form['pass']
    selerid = request.form['selerid']
    lid=request.form['lid']

    qry="SELECT * FROM bank WHERE bank_name = '"+bank_name+"'and account_number= '"+accno+"'and pin_number='"+password+"'"
    ress=db.selectOne(qry)
    if ress is None:
        return jsonify(status="no")
    else:
        qry=" SELECT * FROM `cart` WHERE `u_lid`='"+lid+"' AND `owner_lid`='"+selerid+"'"
        res=db.select(qry)
        tot=0
        for i in res:
            pid=i["product_id"]
            qty=i["quantity"]
            qry1="SELECT price FROM `product` WHERE `product_id`='"+str(pid)+"'"
            res1=db.selectOne(qry1)
            pr=res1['price']
            tot+=float(pr)*float(qty)
            print(tot)

        if  float(ress['balance']) > tot:
            qry="INSERT INTO `ordermain` (`uid`,`status`,`date`,`total`,`toid`,`type`) VALUES('"+str(lid)+"','pending',CURDATE(),'"+str(tot)+"','"+str(selerid)+"','online')"
            orderid=db.insert(qry)

            for i in res:
                pid = i["product_id"]
                qty = i["quantity"]
                qry="INSERT INTO `ordersub` (`ordermain_id`,`product_id`,`quantity`) VALUES ('"+str(orderid)+"','"+str(pid)+"','"+str(qty)+"')"
                print(qry)
                db.insert(qry)

            db.delete("DELETE FROM `cart` WHERE `u_lid`='"+str(lid)+"' AND `owner_lid`='"+selerid+"'")

            qry="INSERT INTO `bill` (`order_id`,`date`,`amount`) VALUES ('"+str(orderid)+"',CURDATE(),'"+str(tot)+"')"
            db.insert(qry)

            b="UPDATE `bank` SET `balance`='"+str(tot)+"' WHERE `bank_name`='"+bank_name+"'AND account_number='"+accno+"'and `pin_number`='"+password+"'"
            db.update(b)
            return jsonify(status="ok")
        else:
            return jsonify(status="low")
@app.route('/user_view_previous_orders',methods=["post"])
def user_view_previous_orders():
    lid=request.form["lid"]
    q="SELECT `ordermain`.*,`entrepreneur`.* FROM `ordermain` INNER JOIN `entrepreneur` ON `ordermain`.`toid`=`entrepreneur`.`ent_login_id` WHERE `ordermain`.`uid`='"+lid+"'"
    d=Db()
    r=d.select(q)
    return jsonify(status="ok",data=r)
@app.route('/user_view_previous_order_more',methods=["post"])
def user_view_previous_order_more():
    oid=request.form["oid"]
    q="SELECT `ordersub`.`ordermain_id`,`ordersub`.`product_id`,`ordersub`.`quantity` AS qty ,`product`.* FROM `ordersub` INNER JOIN `product` ON `ordersub`.`product_id`=`product`.`product_id` WHERE `ordersub`.`ordermain_id`='"+oid+"'"
    d=Db()
    res=d.select(q)
    return jsonify(status="ok",data=res)
@app.route('/user_reviewsadd',methods=['post'])
def user_reviewsadd():
    rating=request.form["rating"]
    uid=request.form["uid"]
    review=request.form["review"]
    shopid=request.form["shopid"]
    totype=request.form["totype"]
    db=Db()
    qry="INSERT INTO `feedback` (`fromlid`,`date`,`rating`,`feedback`,`totype`,`tolid`) VALUES ('"+uid+"',CURDATE(),'"+rating+"','"+review+"','"+totype+"','"+shopid+"')"
    print(qry)
    db.insert(qry)

    return jsonify(status='ok')


@app.route('/delete_product',methods=["post"])
def delete_product():
    pid=request.form["pid"]
    q="DELETE FROM `product` WHERE `product_id`='"+pid+"'"
    c=Db()
    c.delete(q)
    return jsonify(status="ok")

if __name__ == '__main__':
    app.run(debug=True, host="0.0.0.0")

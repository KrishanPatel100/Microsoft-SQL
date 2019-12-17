import pyodbc

server = 'DESKTOP-V18U6ND,1433'
database = 'ClothesShop'
username = '[username]'
password = '[password]'
cnxn = pyodbc.connect(
    'DRIVER={ODBC Driver 17 for SQL Server};SERVER=' + server + ';DATABASE=' + database + ';UID=' + username + ';PWD=' + password)
cursor = cnxn.cursor()


#   The print_clothes_inventory() function does not take in any inputs.
#       This function retrieves all of the data from the ClothesInventory table and displays it.
def print_clothes_inventory():
    try:
        print()
        print('Start print_clothes_inventory():')
        cursor.execute("SELECT * FROM ClothesInventory;")
        row = cursor.fetchone()
        while row:
            print(row[0] + ' x ' + str(row[1]))
            row = cursor.fetchone()
    except:
        print('An error occurred in print_clothes_inventory().')


#   The purchase_item() function takes in 2 inputs: the name of an item and the quantity of the item you would like to purchase.
#       This function will decrease the current quantity of the item by the quantity you want to purchase.
#       After this transaction is committed, the print_clothes_inventory() function will display the current data from the ClothesInventory table.
def purchase_item(itemName, quantity):
    try:
        print()
        print('Start purchase_item():')
        cursor.execute("UPDATE ClothesInventory SET quantity = ((SELECT quantity FROM ClothesInventory WHERE item = '"
                       + itemName + "') - " + quantity + ") WHERE item = '" + itemName + "';")
        cnxn.commit()
        print_clothes_inventory()
    except:
        print('An error occurred in purchase_item().')


#   The return_item() function takes in 2 inputs: the name of an item and the quantity of the item you would like to return.
#       This function will increase the current quantity of the item by the quantity you want to return.
#       After this transaction is committed, the print_clothes_inventory() function will display the current data from the ClothesInventory table.
def return_item(itemName, quantity):
    try:
        print()
        print('Start return_item():')
        cursor.execute("UPDATE ClothesInventory SET quantity = ((SELECT quantity FROM ClothesInventory WHERE item = '"
                       + itemName + "') + " + quantity + ") WHERE item = '" + itemName + "';")
        cnxn.commit()
        print_clothes_inventory()
    except:
        print('An error occurred in return_item().')


#   The insert_new_item() function takes in 2 inputs: the name of an item and an initial quantity for the item.
#       This function will create a new row in the table ClothesInventory and insert both input values into the row.
#       After this transaction is committed, the print_clothes_inventory() function will display the current data from the ClothesInventory table.
def insert_new_item(itemName, quantity):
    try:
        print()
        print('Start insert_new_item():')
        cursor.execute("INSERT INTO ClothesInventory VALUES ('" + itemName + "', " + quantity + ");")
        cnxn.commit()
        print_clothes_inventory()
    except:
        print('An error occurred in insert_new_item().')


#   The delete_item() function takes in 1 input: the name of an item.
#       This function will delete the row where the item name matches the input value.
#       After this transaction is committed, the print_clothes_inventory() function will display the current data from the ClothesInventory table.
def delete_item(itemName):
    try:
        print()
        print('Start delete_item():')
        cursor.execute("DELETE FROM ClothesInventory WHERE item = '" + itemName + "');")
        cnxn.commit()
        print_clothes_inventory()
    except:
        print('An error occurred in delete_item().')


# The main_program() function executes the functions above.
def main_program():
    try:
        print('Start main_program():')
        print_clothes_inventory()  # Print the original (unaltered) table of data
        insert_new_item('Cyan shirt', 5)
        purchase_item('Cyan shirt', 3)
        purchase_item('Yellow shirt', 1)
        return_item('Black shirt', 4)
        delete_item('Blue shirt')
    except:
        print('An error occurred in main_program().')
    finally:
        print('End of main_program().')


main_program()  # Execute main_program()
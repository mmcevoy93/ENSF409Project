import psycopg2

def test_postgresql_connection(host, port, database, user, password):
    try:
        # Attempt to connect to the PostgreSQL server
        conn = psycopg2.connect(
            host=host,
            port=port,
            database=database,
            user=user,
            password=password
        )
        
        # If connection is successful, print a success message
        print("Connection to PostgreSQL database successful!")
        
        # Close the connection
        conn.close()
        
    except Exception as e:
        # If an error occurs, print the error message
        print(f"Error: {e}")

# Provide your PostgreSQL connection details here
host = 'localhost'  # Change this to your PostgreSQL host
port = '5432'       # Change this to your PostgreSQL port
database = 'ewr'  # Change this to your PostgreSQL database name
user = 'oop'   # Change this to your PostgreSQL username
password = 'ucalgary'  # Change this to your PostgreSQL password

# Call the function to test the connection
test_postgresql_connection(host, port, database, user, password)
import requests
import json
import psycopg2


conn = psycopg2.connect(
    database="postgres",
    user="postgres",
    password="soe37",
    host="localhost",
    port=5432
)

cur = conn.cursor()


api_key = ...


total_movies = 1000


current_page = 1


# Bara hægt að fá eina síðu í einu og hver síðan hefur aðeins 20 myndir
max_pages = total_movies/20




cnt = 1


while current_page <= max_pages:
    url = f'https://api.themoviedb.org/3/discover/movie?api_key={api_key}&sort_by=vote_count.desc&page={current_page}'
    response = requests.get(url)


    
    if response.status_code == 200:
        data = response.json()
        results = data.get('results', [])


        if results:
            for i, movie in enumerate(results[:20], start=1):
                title = movie.get('title', 'N/A')
                release_date = movie.get('release_date', 'N/A').split('-')[0]
                poster_path = movie.get('poster_path')
                poster_url = f'https://image.tmdb.org/t/p/w500{poster_path}'
                print(f"{cnt}. Title: {title}")
                print(f"   Year: {release_date}")
                print(f"   Poster URL: {poster_url}")
                
                
                insert_query = "INSERT INTO movies (imageurl, title, year) VALUES (%s, %s, %s)"
                values = (poster_url,title, release_date)
                cur.execute(insert_query, values)
                
                cnt += 1
        else:
            print(f"No results found on Page {current_page}.")


        current_page += 1
    else:
        print(f"Error making the request on Page {current_page}.")
        break

        
        
# Commit the transaction
conn.commit()

# Close the cursor and connection
cur.close()
conn.close()
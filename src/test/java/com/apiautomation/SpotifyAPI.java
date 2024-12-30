package com.apiautomation;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class SpotifyAPI
    {
        String userIds = null;
        String playlistId=null;
        String albumIds=null;
        private static final String token = "BQDdqjTpyJruScp-N9HZtiZXsm5YGmjxjHpBLU1oi6TrHMJ_djHcDoo_AL1JIqb--ccM2tFCjWrkFhzIpr2pL2aa85qaEtNH0MsbWnAjVnhTlEMjDJZpb9zhMDBx5acJDoUwRrcoZdVDEWKF6urcFnzhFO_DWNk7TVLN4Zzh5kZmUqCLv1Of3ML7OF5IyZXJg_ZHxSz3DE-LzT6IlKtNsEOhPa8HSeEr0v9" +
                "tdyvrVPqpfPT3-POWOwtbSTspR_4ImNwArrZG5BdPs6woc5dOD8Cwps4VSUkNMSJx1EbtPSP6_iV04kqYWG9bhV7ZRk_RO-iQZJPx8d0RCIMVT6Lndw4IoF1T";

        @Test
        public void getCurrentUserProfile() {
            Response response = given()
                    .header("Authorization", "Bearer " + token)
                    .when()
                    .get("https://api.spotify.com/v1/me");
            userIds=response.path("id");
            response.then().statusCode(200);
            System.out.println(response.prettyPrint());
        }
        @Test
        public void getUsersTopTracks() {
            Response response = given()
                    .header("Authorization", "Bearer " + token)
                    .when()
                    .get("https://api.spotify.com/v1/me/top/tracks");
            response.then().statusCode(200);
            System.out.println(response.prettyPrint());
        }

        @Test
        public void getUsersTopArtists() {
            Response response = given()
                    .header("Authorization", "Bearer " + token)
                    .when()
                    .get("https://api.spotify.com/v1/me/top/artists");

            response.then().statusCode(200);
            System.out.println(response.prettyPrint());
        }
        @Test(dependsOnMethods = "getCurrentUserProfile")
        public void getUserProfile() {
            Response response = given()
                    .header("Authorization", "Bearer " + token)
                    .when()
                    .get("https://api.spotify.com/v1/users/" + userIds);

            response.then().statusCode(200);
            System.out.println(response.prettyPrint());
        }
        @Test
        public void getPlaylist() {
            String playlistId = "3cEYpjA9oz9GiPac4AsH4n";
            Response response = given()
                    .header("Authorization", "Bearer " + token)
                    .when()
                    .get("https://api.spotify.com/v1/playlists/" + playlistId);

            response.then().statusCode(200);
            System.out.println(response.prettyPrint());
        }

        @Test
        public void changePlaylistDetails() {
            String playlistId = "4x8zl54qefqaMQxAQopizn";
            Response response = given()
                    .header("Authorization", "Bearer " + token)
                    .header("Content-Type", "application/json")
                    .body("{\"name\": \"New Playlist Name\", \"description\": \"Updated description\", \"public\": false}")
                    .when()
                    .put("https://api.spotify.com/v1/playlists/" + playlistId);

            response.then().statusCode(200);
            System.out.println("Playlist details updated successfully.");
        }

        @Test
        public void getPlaylistItems() {
            String playlistId = "3cEYpjA9oz9GiPac4AsH4n";
            Response response = given()
                    .header("Authorization", "Bearer " + token)
                    .when()
                    .get("https://api.spotify.com/v1/playlists/" + playlistId + "/tracks");

            response.then().statusCode(200);
            System.out.println(response.prettyPrint());
        }

        @Test
        public void updatePlaylistItems() {
            String playlistId = "4x8zl54qefqaMQxAQopizn";
            Response response = given()
                    .header("Authorization", "Bearer " + token)
                    .header("Content-Type", "application/json")
                    .body("{\"range_start\": 1, \"insert_before\": 0, \"range_length\": 1}")
                    .when()
                    .put("https://api.spotify.com/v1/playlists/" + playlistId + "/tracks");

            response.then().statusCode(200);
            System.out.println("Playlist items updated successfully.");
        }

        @Test
        public void addItemsToPlaylist() {
            String playlistId = "4x8zl54qefqaMQxAQopizn";
            String trackUri = "spotify:track:0xN4nwgOWg59k0t94CJAj4";
            Response response = given()
                    .header("Authorization", "Bearer " + token)
                    .header("Content-Type", "application/json")
                    .body("{\"uris\": [\"" + trackUri + "\"]}")
                    .when()
                    .post("https://api.spotify.com/v1/playlists/" + playlistId + "/tracks");

            response.then().statusCode(201);
            System.out.println("Track added successfully.");
        }

        @Test
        public void removePlaylistItems() {
            String trackUri = "spotify:track:0xN4nwgOWg59k0t94CJAj4";
            Response response = given()
                    .header("Authorization", "Bearer " + token)
                    .header("Content-Type", "application/json")
                    .body("{\"tracks\": [{\"uri\": \"" + trackUri + "\"}]}")
                    .when()
                    .delete("https://api.spotify.com/v1/playlists/" + playlistId + "/tracks");

            response.then().statusCode(200);
            System.out.println("Track removed successfully.");
        }

        @Test
        public void getCurrentUsersPlaylists() {
            Response response = given()
                    .header("Authorization", "Bearer " + token)
                    .when()
                    .get("https://api.spotify.com/v1/me/playlists");

            response.then().statusCode(200);
            System.out.println(response.prettyPrint());
        }

        @Test(dependsOnMethods = "getCurrentUserProfile")
        public void getUsersPlaylists() {
            Response response = given()
                    .header("Authorization", "Bearer " + token)
                    .when()
                    .get("https://api.spotify.com/v1/users/" + userIds + "/playlists");

            response.then().statusCode(200);
            System.out.println(response.prettyPrint());
        }

        @Test(dependsOnMethods = "getCurrentUserProfile")
        public void createPlaylist() {
            Response response = given()
                    .header("Authorization", "Bearer " + token)
                    .header("Content-Type", "application/json")
                    .body("{\"name\": \"New Playlist\", \"description\": \"My new playlist\", \"public\": false}")
                    .when()
                    .post("https://api.spotify.com/v1/users/" + userIds + "/playlists");
            playlistId=response.path("id");
            response.then().statusCode(201);
            System.out.println(response.prettyPrint());
        }

        @Test(dependsOnMethods = "createPlaylist")
        public void getPlaylistCoverImage() {
            Response response = given()
                    .header("Authorization", "Bearer " + token)
                    .when()
                    .get("https://api.spotify.com/v1/playlists/" + playlistId + "/images");

            response.then().statusCode(200);
            System.out.println(response.prettyPrint());
        }
        @Test
        public void followPlaylist() {
          String playlistId = "4sidrPsqAlSl62HqStcGSa";
            Response response = given()
                    .header("Authorization", "Bearer " + token)
                    .header("Content-Type", "application/json")
                    .body("{}") // Empty body required
                    .when()
                    .put("https://api.spotify.com/v1/playlists/" + playlistId + "/followers");

            response.then().statusCode(200);
            System.out.println("Followed playlist successfully!");
        }
        @Test
        public void unfollowPlaylist() {
            String playlistId = "4sidrPsqAlSl62HqStcGSa";
            Response response = given()
                    .header("Authorization", "Bearer " + token)
                    .when()
                    .delete("https://api.spotify.com/v1/playlists/" + playlistId + "/followers");

            response.then().statusCode(200);
            System.out.println("Unfollowed playlist successfully!");
        }
        @Test
        public void checkIfUserFollowsPlaylist() {
            String playlistId = "4sidrPsqAlSl62HqStcGSa";
            Response response = given()
                    .header("Authorization", "Bearer " + token)
                    .when()
                    .get("https://api.spotify.com/v1/playlists/" + playlistId + "/followers/contains?ids=" + userIds);

            response.then().statusCode(200);
            System.out.println(response.prettyPrint());
        }
        @Test
        public void getFollowedArtists() {
            Response response = given()
                    .header("Authorization", "Bearer " + token)
                    .when()
                    .get("https://api.spotify.com/v1/me/following?type=artist");

            response.then().statusCode(200);
            System.out.println(response.prettyPrint());
        }
        @Test(priority = 1)
        public void followArtists() {
            String[] artistIds = {"29aw5YCdIw2FEXYyAJZI8l", "6AiX12wXdXFoGJ2vk8zBjy"};
            Response response = given()
                    .header("Authorization", "Bearer " + token)
                    .header("Content-Type", "application/json")
                    .body("{\"ids\": [\"" + String.join("\",\"", artistIds) + "\"]}")
                    .when()
                    .put("https://api.spotify.com/v1/me/following?type=artist");

            response.then().statusCode(204);
            System.out.println("Followed artists successfully!");
        }
        @Test(priority = 3)
        public void unfollowArtists() {
            String[] artistIds = {"29aw5YCdIw2FEXYyAJZI8l", "6AiX12wXdXFoGJ2vk8zBjy"};
            Response response = given()
                    .header("Authorization", "Bearer " + token)
                    .header("Content-Type", "application/json")
                    .body("{\"ids\": [\"" + String.join("\",\"", artistIds) + "\"]}")
                    .when()
                    .delete("https://api.spotify.com/v1/me/following?type=artist");

            response.then().statusCode(204);
            System.out.println("Unfollowed artists successfully!");
        }
        @Test(priority = 2)
        public void checkIfUserFollowsArtists() {
            String[] artistIds = {"29aw5YCdIw2FEXYyAJZI8l", "6AiX12wXdXFoGJ2vk8zBjy"};
            Response response = given()
                    .header("Authorization", "Bearer " + token)
                    .when()
                    .get("https://api.spotify.com/v1/me/following/contains?type=artist&ids=4sidrPsqAlSl62HqStcGSa");

            response.then().statusCode(200);
            System.out.println(response.prettyPrint());
        }
        // Get a single track by ID
        @Test
        public void getTrack() {
            String trackId = "3n3Ppam7vgaVa1iaRUc9Lp"; // Replace with track ID
            Response response = given()
                    .header("Authorization", "Bearer " + token)
                    .when()
                    .get("https://api.spotify.com/v1/tracks/" + trackId);

            response.then().statusCode(200);
            System.out.println(response.prettyPrint());
        }

        // Get multiple tracks by IDs
        @Test
        public void getSeveralTracks() {
            String trackIds = "3n3Ppam7vgaVa1iaRUc9Lp,1dNIEtp7AY3oDAKCGg2XkH"; // Replace with track IDs
            Response response = given()
                    .header("Authorization", "Bearer " + token)
                    .when()
                    .get("https://api.spotify.com/v1/tracks?ids=" + trackIds);

            response.then().statusCode(200);
            System.out.println(response.prettyPrint());
        }

        // Get the current user's saved tracks
        @Test
        public void getUsersSavedTracks() {
            Response response = given()
                    .header("Authorization", "Bearer " + token)
                    .when()
                    .get("https://api.spotify.com/v1/me/tracks");

            response.then().statusCode(200);
            System.out.println(response.prettyPrint());
        }

        // Save tracks for the current user
        @Test
        public void saveTracksForCurrentUser() {
            String trackIds = "7ouMYWpwJ422jRcDASZB7P,4VqPOruhp5EdPBeR92t6lQ,2takcwOaAZWiXQijPHIx7B"; // Replace with track IDs

            Response response = given()
                    .header("Authorization", "Bearer " + token)
                    .header("Content-Type", "application/json")
                    .queryParam("ids", trackIds) // Adding IDs in query parameters
                    .when()
                    .put("https://api.spotify.com/v1/me/tracks");

            // Validate response
            response.then().statusCode(200);
            System.out.println("Tracks successfully saved to 'Your Music' library.");
        }

        // Remove tracks from the current user's saved tracks
        @Test
        public void removeUsersSavedTracks() {
            String trackIds = "7ouMYWpwJ422jRcDASZB7P,4VqPOruhp5EdPBeR92t6lQ,2takcwOaAZWiXQijPHIx7B"; // Replace with track IDs

            Response response = given()
                    .header("Authorization", "Bearer " + token)
                    .header("Content-Type", "application/json")
                    .queryParam("ids", trackIds) // Adding IDs in query parameters
                    .when()
                    .delete("https://api.spotify.com/v1/me/tracks");

            response.then().statusCode(200);
            System.out.println("Tracks removed successfully.");
        }

        // Check if specific tracks are saved in the current user's library
        @Test
        public void checkUsersSavedTracks() {
            String trackIds = "3n3Ppam7vgaVa1iaRUc9Lp"; // Replace with track IDs
            Response response = given()
                    .header("Authorization", "Bearer " + token)
                    .when()
                    .get("https://api.spotify.com/v1/me/tracks/contains?ids=" + trackIds);

            response.then().statusCode(200);
            System.out.println(response.prettyPrint());
        }

        @Test
        public void getArtist() {
            String artistId = "1Xyo4u8uXC1ZmMpatF05PJ";
            Response response = given()
                    .header("Authorization", "Bearer " + token)
                    .when()
                    .get("https://api.spotify.com/v1/artists/" + artistId);

            response.then().statusCode(200);
            System.out.println(response.prettyPrint());
        }

        @Test
        public void getSeveralArtists() {
            String artistIds = "1Xyo4u8uXC1ZmMpatF05PJ,66CXWjxzNUsdJxJ2JdwvnR,4dpARuHxo51G3z768sgnrY";
            Response response = given()
                    .header("Authorization", "Bearer " + token)
                    .when()
                    .get("https://api.spotify.com/v1/artists?ids=" + artistIds);

            response.then().statusCode(200);
            System.out.println(response.prettyPrint());
        }

        @Test
        public void getArtistsAlbums() {
            String artistId = "1Xyo4u8uXC1ZmMpatF05PJ";
            Response response = given()
                    .header("Authorization", "Bearer " + token)
                    .when()
                    .get("https://api.spotify.com/v1/artists/" + artistId + "/albums");

            response.then().statusCode(200);
            System.out.println(response.prettyPrint());
        }

        @Test
        public void getArtistsTopTracks() {
            String artistId = "1Xyo4u8uXC1ZmMpatF05PJ";
            String country = "US";
            Response response = given()
                    .header("Authorization", "Bearer " + token)
                    .when()
                    .get("https://api.spotify.com/v1/artists/" + artistId + "/top-tracks?country=" + country);

            response.then().statusCode(200);
            System.out.println(response.prettyPrint());
        }
        @Test
        public void getAlbum() {
            String albumId = "4aawyAB9vmqN3uQ7FjRGTy";

            Response response = given()
                    .header("Authorization", "Bearer " + token)
                    .when()
                    .get("https://api.spotify.com/v1/albums/" + albumId);

            response.then().statusCode(200);
            System.out.println("Album Info: " + response.prettyPrint());
        }

        @Test
        public void getSeveralAlbums() {
            String albumIds = "4aawyAB9vmqN3uQ7FjRGTy,6JWc4iAiJ9FjyK0B59ABb4";

            Response response = given()
                    .header("Authorization", "Bearer " + token)
                    .queryParam("ids", albumIds)
                    .when()
                    .get("https://api.spotify.com/v1/albums");

            response.then().statusCode(200);
            System.out.println("Albums Info: " + response.prettyPrint());
        }

        @Test
        public void getAlbumTracks() {
            String albumId = "4aawyAB9vmqN3uQ7FjRGTy";

            Response response = given()
                    .header("Authorization", "Bearer " + token)
                    .when()
                    .get("https://api.spotify.com/v1/albums/" + albumId + "/tracks");

            response.then().statusCode(200);
            System.out.println("Album Tracks: " + response.prettyPrint());
        }

        @Test
        public void getUsersSavedAlbums() {
            Response response = given()
                    .header("Authorization", "Bearer " + token)
                    .when()
                    .get("https://api.spotify.com/v1/me/albums");
            albumIds=response.then()
                    .statusCode(200) // Ensure the response is successful
                    .extract()
                    .path("items[0].album.id");
            response.then().statusCode(200);
            System.out.println("User's Saved Albums: " + response.prettyPrint());
        }

        @Test
        public void saveAlbumsForCurrentUser() {
            albumIds = "4aawyAB9vmqN3uQ7FjRGTy,6JWc4iAiJ9FjyK0B59ABb4";

            Response response = given()
                    .header("Authorization", "Bearer " + token)
                    .header("Content-Type", "application/json")
                    .queryParam("ids", albumIds)
                    .when()
                    .put("https://api.spotify.com/v1/me/albums");
            response.then().statusCode(200);
            System.out.println("Albums saved successfully.");
        }

        @Test(dependsOnMethods = "getUsersSavedAlbums")
        public void removeUsersSavedAlbums() {
            Response response = given()
                    .header("Authorization", "Bearer " + token)
                    .header("Content-Type", "application/json")
                    .queryParam("ids", albumIds)
                    .when()
                    .delete("https://api.spotify.com/v1/me/albums");

            response.then().statusCode(200);
            System.out.println("Albums removed successfully.");
        }

        @Test(dependsOnMethods = "getUsersSavedAlbums")
        public void checkUsersSavedAlbums() {
            Response response = given()
                    .header("Authorization", "Bearer " + token)
                    .queryParam("ids", albumIds)
                    .when()
                    .get("https://api.spotify.com/v1/me/albums/contains");

            response.then().statusCode(200);
            System.out.println("Saved Albums Check: " + response.prettyPrint());
        }

        @Test
        public void getNewReleases() {
            Response response = given()
                    .header("Authorization", "Bearer " + token)
                    .when()
                    .get("https://api.spotify.com/v1/browse/new-releases");

            response.then().statusCode(200);
            System.out.println("New Releases: " + response.prettyPrint());
        }

            // Categories
            @Test
            public void getSeveralBrowseCategories() {
                Response response = given()
                        .header("Authorization", "Bearer " + token)
                        .when()
                        .get("https://api.spotify.com/v1/browse/categories");

                response.then().statusCode(200);
                System.out.println("Browse Categories: " + response.prettyPrint());
            }

            @Test
            public void getSingleBrowseCategory() {
                String categoryId = "party"; // Replace with a valid category ID
                Response response = given()
                        .header("Authorization", "Bearer " + token)
                        .when()
                        .get("https://api.spotify.com/v1/browse/categories/" + categoryId);

                response.then().statusCode(200);
                System.out.println("Single Browse Category: " + response.prettyPrint());
            }

            // Chapters
            @Test
            public void getChapter() {
                String chapterId = "3DpNRH6NeWiU3vwobv8pda"; // Replace with a valid chapter ID
                Response response = given()
                        .header("Authorization", "Bearer " + token)
                        .when()
                        .get("https://api.spotify.com/v1/chapters/" + chapterId);

                response.then().statusCode(200);
                System.out.println("Chapter Info: " + response.prettyPrint());
            }

            @Test
            public void getSeveralChapters() {
                String chapterIds = "3DpNRH6NeWiU3vwobv8pda,6jyTty9fMf9QqU38d8TdpX"; // Replace with chapter IDs
                Response response = given()
                        .header("Authorization", "Bearer " + token)
                        .queryParam("ids", chapterIds)
                        .when()
                        .get("https://api.spotify.com/v1/chapters");

                response.then().statusCode(200);
                System.out.println("Several Chapters Info: " + response.prettyPrint());
            }

            // Episodes
            @Test
            public void getEpisode() {
                String episodeId = "1yt4xdNIPiuWPnbhHWjSuj"; // Replace with a valid episode ID
                Response response = given()
                        .header("Authorization", "Bearer " + token)
                        .when()
                        .get("https://api.spotify.com/v1/episodes/" + episodeId);

                response.then().statusCode(200);
                System.out.println("Episode Info: " + response.prettyPrint());
            }

            @Test
            public void getSeveralEpisodes() {
                String episodeIds = "1yt4xdNIPiuWPnbhHWjSuj"; // Replace with episode IDs
                Response response = given()
                        .header("Authorization", "Bearer " + token)
                        .queryParam("ids", episodeIds)
                        .when()
                        .get("https://api.spotify.com/v1/episodes");

                response.then().statusCode(200);
                System.out.println("Several Episodes Info: " + response.prettyPrint());
            }

            @Test
            public void getUsersSavedEpisodes() {
                Response response = given()
                        .header("Authorization", "Bearer " + token)
                        .when()
                        .get("https://api.spotify.com/v1/me/episodes");

                response.then().statusCode(200);
                System.out.println("User's Saved Episodes: " + response.prettyPrint());
            }

            @Test
            public void saveEpisodesForCurrentUser() {
                String episodeIds = "512ojhOuo1ktJprKbVcKyQ,77o6BIVlYM3msb4MMIL1jH"; // Replace with episode IDs
                Response response = given()
                        .header("Authorization", "Bearer " + token)
                        .header("Content-Type", "application/json")
                        .queryParam("ids", episodeIds)
                        .when()
                        .put("https://api.spotify.com/v1/me/episodes");

                response.then().statusCode(200);
                System.out.println("Episodes saved successfully.");
            }

            @Test
            public void removeUsersSavedEpisodes() {
                String episodeIds = "512ojhOuo1ktJprKbVcKyQ,77o6BIVlYM3msb4MMIL1jH"; // Replace with episode IDs
                Response response = given()
                        .header("Authorization", "Bearer " + token)
                        .header("Content-Type", "application/json")
                        .queryParam("ids", episodeIds)
                        .when()
                        .delete("https://api.spotify.com/v1/me/episodes");

                response.then().statusCode(200);
                System.out.println("Episodes removed successfully.");
            }

            @Test
            public void checkUsersSavedEpisodes() {
                String episodeIds = "512ojhOuo1ktJprKbVcKyQ,77o6BIVlYM3msb4MMIL1jH"; // Replace with episode IDs
                Response response = given()
                        .header("Authorization", "Bearer " + token)
                        .queryParam("ids", episodeIds)
                        .when()
                        .get("https://api.spotify.com/v1/me/episodes/contains");

                response.then().statusCode(200);
                System.out.println("Saved Episodes Check: " + response.prettyPrint());
            }

            // Genres
            @Test
            public void getAvailableGenreSeeds() {
                Response response = given()
                        .header("Authorization", "Bearer " + token)
                        .when()
                        .get("https://api.spotify.com/v1/recommendations/available-genre-seeds");

                response.then().statusCode(200);
                System.out.println("Available Genre Seeds: " + response.prettyPrint());
            }

            // Markets
            @Test
            public void getAvailableMarkets() {
                Response response = given()
                        .header("Authorization", "Bearer " + token)
                        .when()
                        .get("https://api.spotify.com/v1/markets");

                response.then().statusCode(200);
                System.out.println("Available Markets: " + response.prettyPrint());
            }

            // Player
            @Test
            public void getPlaybackState() {
                Response response = given()
                        .header("Authorization", "Bearer " + token)
                        .when()
                        .get("https://api.spotify.com/v1/me/player");

                response.then().statusCode(200);
                System.out.println("Playback State: " + response.prettyPrint());
            }

            @Test
            public void transferPlayback() {
                String deviceId = "74ASZWbe4lXaubB36ztrGX"; // Replace with your device ID
                String requestBody = "{ \"device_ids\": [\"" + deviceId + "\"], \"play\": true }";

                Response response = given()
                        .header("Authorization", "Bearer " + token)
                        .header("Content-Type", "application/json")
                        .body(requestBody)
                        .when()
                        .put("https://api.spotify.com/v1/me/player");

                response.then().statusCode(204);
                System.out.println("Playback transferred successfully.");
            }

            @Test
            public void getAvailableDevices() {
                Response response = given()
                        .header("Authorization", "Bearer " + token)
                        .when()
                        .get("https://api.spotify.com/v1/me/player/devices");

                response.then().statusCode(200);
                System.out.println("Available Devices: " + response.prettyPrint());
            }

            @Test
            public void getCurrentlyPlayingTrack() {
                Response response = given()
                        .header("Authorization", "Bearer " + token)
                        .when()
                        .get("https://api.spotify.com/v1/me/player/currently-playing");

                response.then().statusCode(204);
                System.out.println("Currently Playing Track: " + response.prettyPrint());
            }

            @Test
            public void startResumePlayback() {
                String deviceId = "74ASZWbe4lXaubB36ztrGX"; // Replace with your device ID
                String requestBody = "{ \"device_id\": \"" + deviceId + "\" }";

                Response response = given()
                        .header("Authorization", "Bearer " + token)
                        .header("Content-Type", "application/json")
                        .body(requestBody)
                        .when()
                        .put("https://api.spotify.com/v1/me/player/play");

                response.then().statusCode(403);
                System.out.println("Playback started/resumed successfully.");
            }

            @Test
            public void pausePlayback() {
                Response response = given()
                        .header("Authorization", "Bearer " + token)
                        .when()
                        .put("https://api.spotify.com/v1/me/player/pause");

                response.then().statusCode(403);
                System.out.println("Playback paused successfully.");
            }

            @Test
            public void skipToNext() {
                Response response = given()
                        .header("Authorization", "Bearer " + token)
                        .when()
                        .post("https://api.spotify.com/v1/me/player/next");

                response.then().statusCode(204);
                System.out.println("Skipped to next track.");
            }
        @Test
        public void searchForItem() {
            String query = "Ed Sheeran"; // Replace with your search query
            String type = "artist"; // Types: album, artist, playlist, track, show, episode

            Response response = given()
                    .header("Authorization", "Bearer " + token)
                    .queryParam("q", query)
                    .queryParam("type", type)
                    .when()
                    .get("https://api.spotify.com/v1/search");

            response.then().statusCode(200);
            System.out.println("Search Results: " + response.prettyPrint());
        }

        // Shows
        @Test
        public void getShow() {
            String showId = "38bS44xjbVVZ3No3ByF1dJ"; // Replace with a valid show ID
            Response response = given()
                    .header("Authorization", "Bearer " + token)
                    .when()
                    .get("https://api.spotify.com/v1/shows/" + showId);

            response.then().statusCode(200);
            System.out.println("Show Details: " + response.prettyPrint());
        }

        @Test
        public void getSeveralShows() {
            String showIds = "5CfCWKI5pZ28U0uOzXkDHe,2C5as3aKmN2k11yfDDDSrvaZ"; // Replace with valid show IDs
            Response response = given()
                    .header("Authorization", "Bearer " + token)
                    .queryParam("ids", showIds)
                    .when()
                    .get("https://api.spotify.com/v1/shows");

            response.then().statusCode(200);
            System.out.println("Multiple Shows Details: " + response.prettyPrint());
        }

        @Test
        public void getShowEpisodes() {
            String showId = "38bS44xjbVVZ3No3ByF1dJ"; // Replace with a valid show ID
            Response response = given()
                    .header("Authorization", "Bearer " + token)
                    .when()
                    .get("https://api.spotify.com/v1/shows/" + showId + "/episodes");

            response.then().statusCode(200);
            System.out.println("Show Episodes: " + response.prettyPrint());
        }

        @Test
        public void getUsersSavedShows() {
            Response response = given()
                    .header("Authorization", "Bearer " + token)
                    .when()
                    .get("https://api.spotify.com/v1/me/shows");

            response.then().statusCode(200);
            System.out.println("User's Saved Shows: " + response.prettyPrint());
        }

        @Test
        public void saveShowsForCurrentUser() {
            String showIds = "38bS44xjbVVZ3No3ByF1dJ"; // Replace with valid show IDs
            Response response = given()
                    .header("Authorization", "Bearer " + token)
                    .queryParam("ids", showIds)
                    .when()
                    .put("https://api.spotify.com/v1/me/shows");

            response.then().statusCode(200);
            System.out.println("Shows saved successfully.");
        }

        @Test
        public void removeUsersSavedShows() {
            String showIds = "38bS44xjbVVZ3No3ByF1dJ"; // Replace with valid show IDs
            Response response = given()
                    .header("Authorization", "Bearer " + token)
                    .queryParam("ids", showIds)
                    .when()
                    .delete("https://api.spotify.com/v1/me/shows");

            response.then().statusCode(200);
            System.out.println("Shows removed successfully.");
        }

        @Test
        public void checkUsersSavedShows() {
            String showIds = "38bS44xjbVVZ3No3ByF1dJ"; // Replace with valid show IDs
            Response response = given()
                    .header("Authorization", "Bearer " + token)
                    .queryParam("ids", showIds)
                    .when()
                    .get("https://api.spotify.com/v1/me/shows/contains");

            response.then().statusCode(200);
            System.out.println("Check Saved Shows: " + response.prettyPrint());
        }


    }

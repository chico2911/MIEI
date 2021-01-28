using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Server.Models
{
    public class BD
    {
        MySqlConnection conn = new MySqlConnection("server= civereapp.mysql.database.azure.com; user id = civereapp@civereapp;database=app; password = edeuma8.");

        public BD()
        {
            conn.Open();
        }
        public int userExists(String mail, String nick)
        {
            
            string sql = "SELECT * FROM users ";
            MySqlCommand cmd = new MySqlCommand(sql, conn);

            MySqlDataReader rdr = cmd.ExecuteReader();

            while (rdr.Read())
            {
                if (rdr["email"].ToString() == mail)
                    return 1;
                if (rdr["username"].ToString() == nick)
                    return 2;
            }
            rdr.Close();
            return 0;
        }

        public int register(string nome, string mail, string pass, string n1, string n2, int guia)
        {
            
                String sql = "INSERT INTO app.users(username, `email`,password, primeiro_nome,  ultimo_nome, guia) VALUES('" + nome + "','" + mail + "','" + pass + "','" + n1 + "','" + n2 + "','" + guia + "')";
                MySqlCommand cmd = new MySqlCommand(sql, conn);
                cmd.Prepare();
                cmd.ExecuteNonQuery();
                return 1;
            
           
            
        }

        public int login(String email, String pass) 
        {

            string sql = "SELECT * FROM users ";
            MySqlCommand cmd = new MySqlCommand(sql, conn);

            MySqlDataReader rdr = cmd.ExecuteReader();

            while (rdr.Read())
            {
                if (rdr["email"].ToString() == email)
                {
                    if (rdr["password"].ToString() == pass)
                    {
                        closeConn();
                        return 1;
                    }
                    else
                    {
                        closeConn();
                        return 2;
                    }
                }
            }
            closeConn();
            return 3;

        }

        public List<List<string>> ImagesRestaurants(string cidade)
        {

            string sql = "SELECT * FROM Hotels, Morada Where Cidade== Braga ";
            MySqlCommand cmd = new MySqlCommand(sql, conn);
            List<string> list = new List<string>();
            List<string> listnome = new List<string>();
            MySqlDataReader rdr = cmd.ExecuteReader();
            int i = 0;
            string[] lista;
            while (rdr.Read())
            {
                list.Add(rdr["Imagem"].ToString());
                listnome.Add(rdr["Nome"].ToString());
                i++;
            }
            rdr.Close();
            List<List<string>> alist = new List<List<string>>();
            alist.Add(list);
            alist.Add(listnome);
            return alist;
        }

        public List<List<string>> ImagesInterestPoints(string cidade)
        {

            

                string sql = "SELECT * FROM Hotels, Morada Where Cidade== Braga ";
                MySqlCommand cmd = new MySqlCommand(sql, conn);
                List<string> list = new List<string>();
                List<string> listnome = new List<string>();
            MySqlDataReader rdr = cmd.ExecuteReader();
                int i = 0;
                string[] lista;
                while (rdr.Read())
                {
                    list.Add(rdr["Imagem"].ToString());
                    listnome.Add(rdr["Nome"].ToString());
                    i++;
                }
                rdr.Close();
                
                List<List<string>> alist = new List<List<string>>();
                alist.Add(list);
                alist.Add(listnome);
                return alist;
            
        }

        public List<List<string>> ImagesHotels(string cidade)
        {
            

                string sql = "SELECT * FROM Hotels, Morada Where Cidade== Braga ";
                MySqlCommand cmd = new MySqlCommand(sql, conn);
                List<string> list = new List<string>();
            List<string> listnome = new List<string>();
            MySqlDataReader rdr = cmd.ExecuteReader();
                int i = 0;
                string[] lista ;
                while (rdr.Read())
                {
                    list.Add(rdr["Imagem"].ToString());
                listnome.Add(rdr["Nome"].ToString());
                i++;
                }
                rdr.Close();
            List<List<string>> alist = new List<List<string>>();
            alist.Add(list);
            alist.Add(listnome);
            return alist;

        }
        public void closeConn()
        {
            conn.Close();
        }
    }
}
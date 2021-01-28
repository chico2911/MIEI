using MySql.Data.MySqlClient;
using Stripe;
using System;
using System.Collections.Generic;
using System.Text.RegularExpressions;
namespace Server.Models
{
    public class BD
    {
        MySqlConnection conn = new MySqlConnection("server=theli4bdserver.mysql.database.azure.com;userid=li4group@theli4bdserver;password=Edeumaoit8;database=app");

        public BD()
        {
            conn.Open();
        }



        ///////////////////////////////////////////////////////////////////// USERS
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

        public int register(string nome, string mail, string pass, string n1, string n2, string guia,string tel)
        {
            
                String sql = "INSERT INTO app.users(username, `email`,password, primeiro_nome,  ultimo_nome, guia,telemovel) VALUES(@Nome,@Email,@Pass,@N1,@N2,@Guia,@Tel)";
                MySqlCommand cmd = new MySqlCommand(sql, conn);
                cmd.Parameters.AddWithValue("@Nome", nome);
                cmd.Parameters.AddWithValue("@Email", mail);
                cmd.Parameters.AddWithValue("@Pass", pass);
                cmd.Parameters.AddWithValue("@N1", n1);
                cmd.Parameters.AddWithValue("@N2", n2);
                cmd.Parameters.AddWithValue("@Guia", guia);
                cmd.Parameters.AddWithValue("@Tel", tel);

            //cmd.Prepare();
            cmd.ExecuteNonQuery();
            closeConn();
            return 1;
            
           
            
        }

        public User login(String email, String pass) 
        {
            User user = new User();
            string sql = "SELECT * FROM users ";
            MySqlCommand cmd = new MySqlCommand(sql, conn);

            MySqlDataReader rdr = cmd.ExecuteReader();

            while (rdr.Read())
            {
                if (rdr["email"].ToString() == email)
                {
                    if (rdr["password"].ToString() == pass)
                    {
                        
                        user.Primeiro_nome = rdr["primeiro_nome"].ToString();
                        user.Segundo_nome = rdr["ultimo_nome"].ToString();
                        user.Telemovel = rdr["telemovel"].ToString();
                        user.Nome = rdr["username"].ToString();
                        user.Guia= rdr["guia"].ToString();
                        user.id = rdr["user_id"].ToString();
                        user.valido=1;
                        closeConn();
                        return user;
                    }
                    else
                    {
                        user.valido = 0;
                        closeConn();
                        return user;
                    }
                }
            }
            closeConn();
            user.valido = -1;
            return user;

        }


        public int editPhone(User user)
        {
            try
            {

                String sql = "UPDATE users SET `telemovel` = @Tele WHERE `email` = @Email;";

                MySqlCommand cmd = new MySqlCommand(sql, conn);

                cmd.Parameters.AddWithValue("@Email", user.Email);
                cmd.Parameters.AddWithValue("@Tele", user.Telemovel);
                cmd.ExecuteNonQuery();
                closeConn();

                return 1;
            }
            catch (Exception e)
            {
                return -2;
            }
        }


        public int editPass(User user)
        {

            try
            {
                string email = user.Email;
                string tele = user.Telemovel;

                String sql = " UPDATE users SET users.password = @Tel WHERE email = @Email";
                MySqlCommand cmd = new MySqlCommand(sql, conn);
                cmd = new MySqlCommand(sql, conn);
                cmd.Parameters.AddWithValue("@Email", user.Email);
                cmd.Parameters.AddWithValue("@Tel", user.Telemovel);
                cmd.ExecuteNonQuery();
                closeConn();
                return 1;
            }
            catch (Exception e) { return -2; }
        }


        ///////////////////////////////////////////////////////////////////// GETS


        public List<Comentario> getcomments(int id, int tipo)
        {
            ListComments lst = new ListComments();

            string sql = "";


            lst.items = new List<Comentario>();
            Comentario info;
            if (tipo == 0)
                sql = "SELECT users.username , classificacao_restaurantes.comentario as texto, classificacao_restaurantes.valor FROM users, classificacao_restaurantes WHERE classificacao_restaurantes.restaurante_id = @ID AND users.user_id = classificacao_restaurantes.user_id;";
            if (tipo == 1)
                sql = "SELECT users.username , classificacao_pontointeresse.comentario as texto, classificacao_pontointeresse.valor	FROM users, classificacao_pontointeresse WHERE classificacao_pontointeresse.ponto_interesse_id = @ID AND users.user_id = classificacao_pontointeresse.users_id;";
            if (tipo == 2)
                sql = "SELECT users.username , classificacao_hoteis.comentario as texto, classificacao_hoteis.valor FROM users, classificacao_hoteis WHERE classificacao_hoteis.hoteis_id = @ID AND users.user_id = classificacao_hoteis.users_id;";

            MySqlCommand cmd = new MySqlCommand(sql, conn);
            cmd.Parameters.AddWithValue("@ID", id);
            MySqlDataReader rdr = cmd.ExecuteReader();
            string[] lista;
            while (rdr.Read())
            {
                info = new Comentario();
                info.Texto = (rdr["texto"].ToString());
                info.Utilizador = (rdr["username"].ToString());
                info.Votacao = Int32.Parse(rdr["valor"].ToString());
                lst.items.Add(info);
            }

            closeConn();
            return lst.items;



        }

        public List<Excursao> getexcursao(String cidade)
        {
            try
            {
                ListExcursões lst = new ListExcursões();
                lst.items = new List<Excursao>();
                Excursao info;
                string sql = "SELECT excursões.id as id, excursões.guia_id as gui, ponto_interesse.id as idp, excursões.preço as preço, excursões.data_hora as date_hora, excursões.descricao as descr FROM excursões, ponto_interesse, morada Where excursões.ponto_interesse_id=ponto_interesse.id and ponto_interesse.Morada_idMorada=morada.idMorada and morada.cidade = @Cidade AND excursões.data_hora > now(); ";
                MySqlCommand cmd = new MySqlCommand(sql, conn);
                cmd.Parameters.AddWithValue("@Cidade", cidade);
                MySqlDataReader rdr = cmd.ExecuteReader();
                while (rdr.Read())
                {
                    info = new Excursao();
                    info.IdExc = rdr["id"].ToString();
                    info.Id = rdr["idp"].ToString();
                    info.horario = rdr["date_hora"].ToString();
                    info.descricao = rdr["descr"].ToString();
                    info.preço = rdr["preço"].ToString();
                    info.Guia = rdr["gui"].ToString();
                    lst.items.Add(info);
                }
                rdr.Close();
                return lst.items;
            }
            catch(Exception e) { return null; }
        }

        public List<Places> ImagesRestaurants(string cidade, string date)
        {
            string bddate = "";
            string sql = "SELECT versoes_tabelas.restaurantes From versoes_tabelas";
            MySqlCommand cmd = new MySqlCommand(sql, conn);
            MySqlDataReader rdr = cmd.ExecuteReader();
            rdr.Read();
            
            bddate = (rdr["restaurantes"].ToString());
            rdr.Close();

            if (!bddate.Equals(date))
            {

                ListObjects lst = new ListObjects();
            lst.items = new List<Places>();
            Places info;
            sql = "SELECT restaurantes.nome, restaurantes.image_path, restaurantes.descricao,restaurantes.id,restaurantes.classificacao_media, morada.morada as mor, contactos.email as email, contactos.telemovel as telemovel, contactos.telefone as telefone  FROM restaurantes,morada,contactos WHERE restaurantes.Morada_idMorada = morada.idMorada && morada.cidade =@Cidade and restaurantes.Contactos_idContactos = contactos.idContactos;";
            cmd = new MySqlCommand(sql, conn);
            cmd.Parameters.AddWithValue("@Cidade", cidade);
            rdr = cmd.ExecuteReader();
            while (rdr.Read())
            {
                info = new Places();
                info.url=(rdr["image_path"].ToString());
                info.sitio=(rdr["nome"].ToString());
                info.descrição=(rdr["descricao"].ToString());
                info.id=(rdr["id"].ToString());
                info.classificacao=(rdr["classificacao_media"].ToString());
                info.morada = (rdr["mor"].ToString());
                info.mail = (rdr["email"].ToString());
                info.telefone = (rdr["telefone"].ToString());
                info.telemovel = (rdr["telemovel"].ToString());
                info.date = bddate;
                lst.items.Add(info);
            }
            rdr.Close();

            closeConn();

            return lst.items;
            }
            else
            {
                ListObjects lst = new ListObjects();
                lst.items = new List<Places>();
                Places info = new Places();

                info.date = "TRUE";
                   lst.items.Add(info);
                closeConn();

                return lst.items;
            }
        }

        public List<Places> ImagesInterestPoints(string cidade,string date)
        {
            string bddate = "";
            string sql = "SELECT versoes_tabelas.ponto_interesse From versoes_tabelas";
            MySqlCommand cmd = new MySqlCommand(sql, conn);
            MySqlDataReader rdr = cmd.ExecuteReader();
            rdr.Read();

            bddate = (rdr["ponto_interesse"].ToString());
            rdr.Close();

            if (!bddate.Equals(date))
            {
                ListObjects lst = new ListObjects();
                lst.items = new List<Places>();
                Places info;
                sql = "SELECT ponto_interesse.nome, ponto_interesse.image_path, ponto_interesse.descricao,ponto_interesse.id,ponto_interesse.classificacao_media , morada.morada as mor,contactos.email as email, contactos.telemovel as telemovel, contactos.telefone as telefone   FROM ponto_interesse,morada, contactos WHERE ponto_interesse.Morada_idMorada = morada.idMorada && morada.cidade =@Cidade and ponto_interesse.Contactos_idContactos = contactos.idContactos;";

                cmd = new MySqlCommand(sql, conn);
                cmd.Parameters.AddWithValue("@Cidade", cidade);
                rdr = cmd.ExecuteReader();
                string[] lista;
                while (rdr.Read())
                {
                    info = new Places();
                    info.url = (rdr["image_path"].ToString());
                    info.sitio = (rdr["nome"].ToString());
                    info.descrição = (rdr["descricao"].ToString());
                    info.id = (rdr["id"].ToString());
                    info.classificacao = (rdr["classificacao_media"].ToString());
                    info.morada = (rdr["mor"].ToString());
                    info.mail = (rdr["email"].ToString());
                    info.telefone = (rdr["telefone"].ToString());
                    info.telemovel = (rdr["telemovel"].ToString());
                    info.date = bddate;
                    lst.items.Add(info);
                }

                closeConn();
                return lst.items;
            }
            else
            {
                ListObjects lst = new ListObjects();
                lst.items = new List<Places>();
                Places info = new Places();

                info.date = "TRUE";
                lst.items.Add(info);

                closeConn();
                return lst.items;
            }
            
        }

        public List<Places> ImagesHotels(string cidade,string date)
        {
            string bddate = "";
            string sql = "SELECT versoes_tabelas.hoteis From versoes_tabelas";
            MySqlCommand cmd = new MySqlCommand(sql, conn);
            MySqlDataReader rdr = cmd.ExecuteReader();
            rdr.Read();

            bddate = (rdr["hoteis"].ToString());
            rdr.Close();

            if (!bddate.Equals(date))
            {
                ListObjects lst = new ListObjects();
            lst.items = new List<Places>();
            Places info;
            sql = "SELECT hoteis.nome, hoteis.image_path, hoteis.descricao,hoteis.id,hoteis.classificacao_media, morada.morada as mor, contactos.email as email, contactos.telemovel as telemovel, contactos.telefone as telefone   FROM hoteis,morada,contactos WHERE hoteis.Morada_idMorada = morada.idMorada && morada.cidade =@Cidade and hoteis.Contactos_idContactos = contactos.idContactos;";

            cmd = new MySqlCommand(sql, conn);
            cmd.Parameters.AddWithValue("@Cidade", cidade);
            rdr = cmd.ExecuteReader();
            int i = 0;
            while (rdr.Read())
            {
                info = new Places();
                info.url = (rdr["image_path"].ToString());
                info.sitio = (rdr["nome"].ToString());
                info.descrição = (rdr["descricao"].ToString());
                info.id = (rdr["id"].ToString());
                info.classificacao = (rdr["classificacao_media"].ToString());
                info.morada = (rdr["mor"].ToString());
                info.mail = (rdr["email"].ToString());
                info.telefone = (rdr["telefone"].ToString());
                info.telemovel = (rdr["telemovel"].ToString());
                info.date = bddate;
                lst.items.Add(info);
                i++;
            }
            rdr.Close();
            closeConn();
            return lst.items;
            }
            else
            {
                ListObjects lst = new ListObjects();
                lst.items = new List<Places>();
                Places info = new Places();

                info.date = "TRUE";
                lst.items.Add(info);

                closeConn();
                return lst.items;
            }

        }

        public double getprice(string id)
        {
            double soma = 0;
            string sql = "SELECT excursões.preço as saldo FROM excursões where excursões.id = @ID;";
            MySqlCommand cmd = new MySqlCommand(sql, conn);
            cmd.Parameters.AddWithValue("@ID", id);
            MySqlDataReader rdr = cmd.ExecuteReader();
            while (rdr.Read())
            {
                soma += float.Parse(rdr["saldo"].ToString());
            }
            rdr.Close();
            return soma;
        }


        public int checkinsc ( string id,string exc)
        {
            int count = 0;
            string sql = "SELECT *FROM app.inscrição where inscrição.excursão_id = @EX and inscrição.user_id = @ID;";
            MySqlCommand cmd = new MySqlCommand(sql, conn);
            cmd.Parameters.AddWithValue("@ID", id);
            cmd.Parameters.AddWithValue("@EX", exc);
            MySqlDataReader rdr = cmd.ExecuteReader();
            while (rdr.Read())
            {
                count++;
            }
            rdr.Close();
            if (count == 0) return 1;
            else return -1;
            
        }

        ///////////////////////////////////////////////////////////////////// INSC / ADDS
        public int InscExcursao(String IdExc, String Id, String stripe)
        {
            string sql = "Insert into inscrição (excursão_id,user_id,pagamento_id) values  (@Exc,@ID,@STRIPE)";
            MySqlCommand cmd = new MySqlCommand(sql, conn);
            cmd.Parameters.AddWithValue("@Exc", IdExc);
            cmd.Parameters.AddWithValue("@ID", Id);
            cmd.Parameters.AddWithValue("@STRIPE", stripe);
            cmd.ExecuteNonQuery();
            closeConn();
            return 1;
        }

        public int addExcursao(Excursao exc)
        {
            try
            {
                string sql = "Insert into excursões(excursões.data_hora, excursões.ponto_interesse_id,excursões.guia_id,excursões.descricao,excursões.preço) values  (@Horario,@PI,@Guia,@Desc, @Pr)";
                MySqlCommand cmd = new MySqlCommand(sql, conn);
                cmd.Parameters.AddWithValue("@Horario", exc.horario);
                cmd.Parameters.AddWithValue("@PI", exc.PontosInteresse);
                cmd.Parameters.AddWithValue("@Guia", exc.Guia);
                cmd.Parameters.AddWithValue("@Desc", exc.descricao);
                cmd.Parameters.AddWithValue("@Pr", exc.preço);
                cmd.ExecuteNonQuery();
                closeConn();
                return 1;
            }
            catch(Exception e) { return -1; }
        }

        public int addcomment(Comentario comment)
        {
            try
            {
                ListComments lst = new ListComments();

                string sql = "";

                lst.items = new List<Comentario>();
                Comentario info;
                if (comment.tipo == 0)
                    sql = "REPLACE INTO classificacao_restaurantes VALUES (@Ut,@ID,@Va,@Te);";
                if (comment.tipo == 1)
                    sql = "REPLACE INTO classificacao_pontointeresse VALUES (@Ut,@ID,@Va,@Te);";
                if (comment.tipo == 2)
                    sql = "REPLACE INTO classificacao_hoteis VALUES (@Ut,@ID,@Va,@Te);";
                MySqlCommand cmd = new MySqlCommand(sql, conn);
                cmd.Parameters.AddWithValue("@ID", comment.id);
                cmd.Parameters.AddWithValue("@Ut", comment.Utilizador);
                cmd.Parameters.AddWithValue("@Va", comment.Votacao);
                cmd.Parameters.AddWithValue("@Te", comment.Texto);
                cmd.ExecuteNonQuery();


                closeConn();
                return 1;
            }
            catch (Exception e) { return -1; }
        }


        ///////////////////////////////////////////////////////////////////// REMOVE

        public int DesistirExcursao(Excursao exc)
        {
            string sql = "Insert into Excursões values  ('" + exc.horario + "'," + exc.PontosInteresse + "," + exc.Guia + "," + exc.descricao + ")";
            MySqlCommand cmd = new MySqlCommand(sql, conn);
            cmd.Prepare();
            int res = cmd.ExecuteNonQuery();
            return res;
        }

        public void removeExcursao(Excursao exc)
        {
            string idp = "";
            string sql = "Select pagamento_id FROM inscrição WHERE excursão_id = @ID";
            MySqlCommand cmd = new MySqlCommand(sql, conn);
            cmd.Parameters.AddWithValue("@ID", exc.IdExc);
            MySqlDataReader rdr = cmd.ExecuteReader();
            while (rdr.Read())
            {
                idp = (rdr["pagamento_id"].ToString());

                StripeConfiguration.ApiKey = "sk_test_XgPtpokfZ4XKLeqJ1891sNbo00PyeAo83n";

                var options = new RefundCreateOptions
                {
                    PaymentIntent = idp,
                };
                var service = new RefundService();
                service.Create(options);
            }
            rdr.Close();
            sql = "DELETE FROM inscrição WHERE excursão_id = @ID";
            cmd = new MySqlCommand(sql, conn);
            cmd.Parameters.AddWithValue("@ID", exc.IdExc);
            cmd.ExecuteNonQuery();
            sql = "DELETE FROM excursões WHERE id = @ID";
            cmd = new MySqlCommand(sql, conn);
            cmd.Parameters.AddWithValue("@ID", exc.IdExc);
            cmd.ExecuteNonQuery();
           closeConn();
            
        }

        ///////////////////////////////////////////////////////////////////// CONTROLO VERSOES

        public int ControloVersoes(string date)
        {

            ListVersions lst = new ListVersions();
            lst.items = new List<Versoes>();
            string bdlatest = "";
            Versoes info;
            string sql = "SELECT versoes_bd.Versao, versoes_bd.ReleaseDateTime FROM versoes_bd ORDER BY versoes_bd.ReleaseDateTime DESC LIMIT 1;";
            MySqlCommand cmd = new MySqlCommand(sql, conn);
            MySqlDataReader rdr = cmd.ExecuteReader();
            rdr.Read();
            bdlatest = (rdr["ReleaseDateTime"].ToString());



            rdr.Close();
            closeConn();
            if (bdlatest.Equals(date))
                return 1;
            return -1;

        }
        
        ///////////////////////////////////////////////////////////////////// UPDATES
        
        public double saldo(String guia)
        {
            float soma = 0;
            String sql = "SELECT excursões.id, excursões.data_hora, excursões.ponto_interesse_id, excursões.guia_id, excursões.preço as preço FROM excursões WHERE excursões.guia_id = @ID AND excursões.data_hora < now()";
            MySqlCommand cmd = new MySqlCommand(sql, conn);
            cmd.Parameters.AddWithValue("@ID", guia);
            MySqlDataReader rdr = cmd.ExecuteReader();
            while (rdr.Read())
            {
                soma += float.Parse(rdr["preço"].ToString());
            }
            rdr.Close();
            /////
            sql = "DELETE FROM inscrição WHERE(SELECT 1 FROM excursões WHERE inscrição.excursão_id = excursões.id AND excursões.guia_id = @ID AND excursões.data_hora < now());";
            cmd = new MySqlCommand(sql, conn);
            cmd.Parameters.AddWithValue("@ID", guia);
            cmd.ExecuteNonQuery();
            rdr.Close();
            /////
            soma = soma * float.Parse("0.9");
            sql = "Select carteira_guia.Saldo as saldo From carteira_guia WHERE carteira_guia.id_Guia= @ID;";
            cmd = new MySqlCommand(sql, conn);
            cmd.Parameters.AddWithValue("@ID", guia);
            rdr = cmd.ExecuteReader();
            while (rdr.Read())
            {
                soma += float.Parse(rdr["saldo"].ToString());
            }
            rdr.Close();
            ////
            sql = "UPDATE app.`carteira_guia` SET Saldo = @SOM WHERE (id_Guia = @ID);";
            cmd = new MySqlCommand(sql, conn);
            cmd.Parameters.AddWithValue("@ID", guia);
            cmd.Parameters.AddWithValue("@SOM", soma);
            
            cmd.ExecuteNonQuery();

            closeConn();
            return soma;
        }
       
        public int payrequest(string iban, string value, string id)
        {
            try
            {
                string sql = "INSERT INTO pedidos_payout VALUES(@ID, @VAL, 0, @IB);";
                MySqlCommand cmd = new MySqlCommand(sql, conn);
                cmd.Parameters.AddWithValue("@ID", id);
                cmd.Parameters.AddWithValue("@VAL", value);
                cmd.Parameters.AddWithValue("@IB", iban);
                cmd.ExecuteNonQuery();
                closeConn();
                return 1;
            }
            catch(Exception e)
            {
                return 0;
            }
        }
        public void updatesaldo(String guia, double valor)
        {
            string sql = "UPDATE app.`carteira_guia` SET Saldo = @SOM WHERE (id_Guia = @ID);";
            MySqlCommand cmd = new MySqlCommand(sql, conn);
            cmd.Parameters.AddWithValue("@ID", guia);
            cmd.Parameters.AddWithValue("@SOM", valor);
            cmd.ExecuteNonQuery();
            closeConn();
        }
        public double payout(string guia)
        {
            double soma = 0;
            string sql = "SELECT carteira_guia.Saldo as saldo FROM app.carteira_guia where carteira_guia.id_Guia = @ID;";
            MySqlCommand cmd = new MySqlCommand(sql, conn);
            cmd.Parameters.AddWithValue("@ID", guia);
            MySqlDataReader rdr = cmd.ExecuteReader();
            while (rdr.Read())
            {
                soma += float.Parse(rdr["saldo"].ToString());
            }
            rdr.Close();
            return soma;
        }

        public void closeConn()
        {
            conn.Close();
        }
    }
}
﻿using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Net;
using System.Runtime.Serialization;
using System.Security.Cryptography;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;
using static System.String;

namespace NoDB
{
    public class Service1 : IService1
    {

        // returns true if user is authenticated and false if not
        public bool Login(string username, string password)
        {
            // step 1, calculate MD5 hash from input
            var md5 = MD5.Create();
            var inputBytes = Encoding.ASCII.GetBytes(password);
            var hash = md5.ComputeHash(inputBytes);

            // step 2, convert byte array to hex string
            var sb = new StringBuilder();
            foreach (var t in hash)
            {
                sb.Append(t.ToString("X2"));
            }

            var md5Pass = sb.ToString();
            var query = Format("SELECT Count(username) as cnt FROM Uporabnik WHERE username = '{0}' AND geslo = '{1}';", username, md5Pass);

            // check if user exists in database       
            try
            {
                var connectionString = ConfigurationManager.ConnectionStrings["ConnectionString"].ConnectionString;
                var sqlConnection1 = new SqlConnection(connectionString);

                var cmd = new SqlCommand
                {
                    CommandType = CommandType.Text,
                    CommandText = query,
                    Connection = sqlConnection1
                };

                sqlConnection1.Open();
                cmd.ExecuteNonQuery();

                var r = cmd.ExecuteReader();
                r.Read();
                if (r.GetInt32(0) >= 1) return true;
                sqlConnection1.Close();

            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.Message);
            }

            return false;
        }


        // inputs username, message and time of message to database
        public void Send(string username, string message)
        {

        }

        // returns list with messages
        public List<Message> GetMessages(int id)
        {
            return new List<Message>();
        }


    }
}
